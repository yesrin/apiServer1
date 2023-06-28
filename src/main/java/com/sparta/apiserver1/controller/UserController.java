package com.sparta.apiserver1.controller;


import com.sparta.apiserver1.dto.SignupRequestDto;
import com.sparta.apiserver1.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;


//    @PostMapping("/user/login")
//    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
//        log.info("로그인 되었습니다");
//        userService.login(loginRequestDto, response);
//        return "로그인 되었습니다";
//    }

    @PostMapping("/user/signup")
    public String signup(@RequestBody SignupRequestDto requestDto ) {

        userService.signup(requestDto);

        return "회원가입 되었습니다";
    }
}
