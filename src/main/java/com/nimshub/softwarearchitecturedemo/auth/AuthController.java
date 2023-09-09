package com.nimshub.softwarearchitecturedemo.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<RegistrationResponse> register(
            @Valid @RequestBody  RegistrationRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponse> login(
            @RequestBody LoginRequest request
    ) {
        return ResponseEntity.ok(service.login(request));
    }


    @GetMapping("/getcode")
    public ResponseEntity<RegistrationResponse> getVerificationCode(VerificationCodeRequest request) {
        return ResponseEntity.ok(service.getVerificationCode(request));
    }

    @PostMapping({"/verifyEmail"})
    public VerifyEmailResponse verifyEmail(@RequestBody VerifyEmailRequest req) {
        return service.verifyEmail(req.getToken());
    }
}
