package com.nimshub.softwarearchitecturedemo.auth;

import com.nimshub.softwarearchitecturedemo.config.JwtService;
import com.nimshub.softwarearchitecturedemo.exceptions.UserAlreadyExistsException;
import com.nimshub.softwarearchitecturedemo.user.Role;
import com.nimshub.softwarearchitecturedemo.user.User;
import com.nimshub.softwarearchitecturedemo.user.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    private final JavaMailSender mailSender;

    Random random = new Random();

    public RegistrationResponse register(RegistrationRequest request) {
        if (userRepository.existsByEmail(request.getUserName())) {
            throw new UserAlreadyExistsException("User with email - %s already exists".formatted(request.getUserName()));
        }
        String token = UUID.randomUUID().toString();

        var user = User.builder()
                .userId(UUID.randomUUID())
                .firstName(request.getUserFirstName())
                .lastName(request.getUserLastName())
                .email(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword()))
                .verificationCode(token)
                .role(Role.USER)
                .build();
        var registeredUser =userRepository.save(user);

        sendVerificationEmail(registeredUser);
        return RegistrationResponse.builder()
                .userFirstName(registeredUser.getFirstName())
                .userLastName(registeredUser.getLastName())
                .userName(registeredUser.getEmail())
                .build();
    }

    private void sendVerificationEmail(User user) {
        String toAddress = user.getEmail();
        String fromAddress = "ecommercedemo@gmail.com";
        String senderName = "Ecommerce";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "<br><br>"
                + "We hope this email finds you well "
                + "To ensure the security of your account and provide you with a seamless experience,<br>"
                + "Here is your verification code <b>[[code]]</b>:<br>"
                + "<br>"
                + "Thank you for choosing Our Ecommerce platform. We're thrilled to have you on board!<br>"
                + "<br><br>"
                + "Safe travels and best regards,<br>"
                + "Ecommerce Team<br>";
        String token = user.getVerificationCode();
        String link = "http://localhost:4200/verification/"+token;
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        content = content.replace("[[name]]", user.getFirstName());
        content = content.replace("[[code]]", link);

        try {
            helper.setFrom(fromAddress, senderName);
            helper.setTo(toAddress);
            helper.setSubject(subject);
            helper.setText(content, true);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        mailSender.send(message);
    }

    public LoginResponse login(LoginRequest request) {
             authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserName(),
                        request.getUserPassword()
                )
        );
        var user = userRepository.findByEmail(request.getUserName())
                .orElseThrow();
        return mapUserToLoginResponse(user);
    }

    public VerifyEmailResponse verifyEmail(String token) {
        User user= userRepository.findByVerificationCode(token)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Incorrect Verification Code or User with Verification Code - %s does not exist".formatted(token)));
        user.setEnabled(true);
        userRepository.save(user);
        return new VerifyEmailResponse("Email Verified");
    }

    public LoginResponse mapUserToLoginResponse(User user){
        var jwtToken = jwtService.generateToken(user);
        return LoginResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public RegistrationResponse getVerificationCode(VerificationCodeRequest request){
        var user = userRepository.findByEmail(request.getUserEmail())
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Incorrect Email or User with E-mail - %s does not exist".formatted(request.getUserEmail())));
        sendVerificationEmail(user);
        return RegistrationResponse.builder()
                .userFirstName(user.getFirstName())
                .userLastName(user.getLastName())
                .userName(user.getEmail())
                .build();
    }
}
