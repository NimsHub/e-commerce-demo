package com.nimshub.softwarearchitecturedemo.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegistrationRequest {
    @NotBlank(message = "firstname is required")
    private String userFirstName;

    private String userLastName;
    @NotBlank(message = "email is required")
    private String userName;
    @NotBlank(message = "password is required")
    private String password;
}
