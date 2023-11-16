package com.fastfood.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AuthenticationRequest {

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String password;
}
