package com.sparta.apiserver1.controller;


import com.sparta.apiserver1.dto.SignupRequestDto;
import com.sparta.apiserver1.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping("/user/login-page")
    public String loginPage() {
        return "로그인하세요";
    }
    @GetMapping("/user/signup")
    public String signupPage() {
        return "회원가입하세요";
    }

    @PostMapping("/user/signup")
    public String signup(@RequestBody SignupRequestDto requestDto ) {

        userService.signup(requestDto);

        return "회원가입 되었습니다";
    }
}
