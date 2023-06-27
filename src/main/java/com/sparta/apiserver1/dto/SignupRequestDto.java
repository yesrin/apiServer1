package com.sparta.apiserver1.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @NotBlank
    private String username;

    @NotBlank
    private String password;


    @NotBlank
    private String email;
    private boolean admin = false;
    private String adminToken = "";
}