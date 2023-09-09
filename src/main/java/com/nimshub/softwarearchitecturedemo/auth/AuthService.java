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
import org.springframework.security.authentication.BadCredentialsException;
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
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("User with email - %s already exists".formatted(request.getEmail()));
        }
        var user = User.builder()
                .userId(UUID.randomUUID())
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .verificationCode(generateVerificationCode())
                .role(Role.USER)
                .build();
        var registeredUser =userRepository.save(user);

        sendVerificationEmail(registeredUser);
        return RegistrationResponse.builder()
                .message("Verification Email is Sent to Your Email")
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

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        content = content.replace("[[name]]", user.getFirstName());
        content = content.replace("[[code]]", user.getVerificationCode());

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
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        return mapUserToLoginResponse(user);
    }

    public String generateVerificationCode() {
        int codeLength = 6;
        int minCodeValue = (int) Math.pow(10, codeLength - 1); // Minimum value with 6 digits
        int maxCodeValue = (int) Math.pow(10, codeLength) - 1; // Maximum value with 6 digits

        int verifyCode = (random.nextInt(maxCodeValue - minCodeValue + 1) + minCodeValue);
        return String.valueOf(verifyCode);
    }

    public LoginResponse verifyEmail(VerifyUserRequest request) {
        User user = userRepository.findByEmail(request.getUserEmail()).orElseThrow(() -> new UsernameNotFoundException(
                "Incorrect Email or User with E-mail - %s does not exist".formatted(request.getUserEmail())));

        // Compare the provided verification code with the stored code or the default code (for testing)
        if(user.getVerificationCode().equals(request.getVerificationCode())
                || request.getVerificationCode().equals("123456")){
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepository.save(user);
            return mapUserToLoginResponse(user);
        }else{
            // Incorrect verification code
            throw new BadCredentialsException("Incorrect verification code");
        }
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
                .message("Verification Email is Sent to Your Email")
                .build();
    }
}
