package com.sparta.apiserver1.User.dto;

import com.sparta.apiserver1.User.entity.UserRoleEnum;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequestDto {
    private String username;
    private String password;

    private UserRoleEnum role;
}