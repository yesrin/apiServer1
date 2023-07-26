//package com.sparta.apiserver1.Common.security;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sparta.apiserver1.User.dto.LoginRequestDto;
//import com.sparta.apiserver1.User.entity.UserRoleEnum;
//import com.sparta.apiserver1.Common.jwt.JwtUtil;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.MessageSource;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import java.io.IOException;
//import java.util.Locale;
//
//@Slf4j(topic = "로그인 및 JWT 생성")
//public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//    private final JwtUtil jwtUtil;
//
//    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
//        this.jwtUtil = jwtUtil;
//        setFilterProcessesUrl("/api/user/login");
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        try {
//            LoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);
//            log.info("로그인 여기?");
//            return getAuthenticationManager().authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            requestDto.getUsername(),
//                            requestDto.getPassword(),
//                            null
//                    )
//            );
//        } catch (IOException e) {
//            log.error(e.getMessage());
//            throw new RuntimeException(e.getMessage());
//        }
//    }
//
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
//        String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
//        UserRoleEnum role = ((UserDetailsImpl) authResult.getPrincipal()).getUser().getRole();
//
//        String token = jwtUtil.createToken(username, role);
//        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
//        log.info("로그인 성공");
//    }
//
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) {
//        log.info("로그인실패");
//        throw new IllegalArgumentException(
//                messageSource.getMessage(
//                        "user.not.found",
//                        null ,
//                        "user not found",
//                        Locale.getDefault()
//                )
//        );
//        response.setStatus(401);
//    }
//
//}