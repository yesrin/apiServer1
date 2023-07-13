package com.sparta.apiserver1.User.controller;


import com.sparta.apiserver1.Common.jwt.JwtUtil;
import com.sparta.apiserver1.User.dto.LoginRequestDto;
import com.sparta.apiserver1.User.dto.SignupRequestDto;
import com.sparta.apiserver1.User.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

@PostMapping("/login")
public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {

    userService.login(loginRequestDto);
    //JWT 생성 및 쿠키에 저장 후 Response 객체에 추가
    response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(loginRequestDto.getUsername(), loginRequestDto.getRole()));
    return "로그인 성공";
}
    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequestDto requestDto ) {

        userService.signup(requestDto);

        return "회원가입 되었습니다";
    }
}
