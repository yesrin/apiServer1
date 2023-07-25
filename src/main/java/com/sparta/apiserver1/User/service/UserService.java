package com.sparta.apiserver1.User.service;

import com.sparta.apiserver1.User.dto.LoginRequestDto;
import com.sparta.apiserver1.User.dto.SignupRequestDto;


public interface UserService {

    void signup(SignupRequestDto requestDto);

    void login(LoginRequestDto loginRequestDto);

}