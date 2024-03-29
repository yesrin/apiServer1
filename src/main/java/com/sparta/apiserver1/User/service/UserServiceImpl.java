package com.sparta.apiserver1.User.service;

import com.sparta.apiserver1.User.dto.LoginRequestDto;
import com.sparta.apiserver1.User.dto.SignupRequestDto;
import com.sparta.apiserver1.User.entity.User;
import com.sparta.apiserver1.User.entity.UserRoleEnum;
import com.sparta.apiserver1.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MessageSource messageSource;

    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    @Override
    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException(
                    messageSource.getMessage(
                            "user.name.duplicate",
                            null,
                            "name is duplicate",
                            Locale.getDefault()
                    )
            );
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException(
                        messageSource.getMessage(
                                "admin.password.different",
                                null,
                                "Administrator password is different",
                                Locale.getDefault()
                        ));
            }
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 등록
        User user = new User(username, password, role);
        userRepository.save(user);
    }
    @Override
    public void login(LoginRequestDto loginRequestDto) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        //사용자 확인 (username 이 없는 경우)
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException(
                        messageSource.getMessage(
                                "user.not.found",
                                null,
                                "user not found",
                                Locale.getDefault()
                        )
                )
        );

        //비밀번호 확인 (password 가 다른 경우)
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("password.not.match");
        }
    }
}
