package com.sparta.apiserver1.User.repository;

import com.sparta.apiserver1.User.entity.User;
import com.sparta.apiserver1.User.entity.UserRoleEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
//core.api 나 HAMCRSET 하위를 사용하면 문제 없을 확률 높음

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("User Insert 데스트")
    void dynamicInsertTest() {

        // given
        User newUser=new User("yerin15","asdaf", UserRoleEnum.ADMIN);

        // when
        var savedUser = userRepository.save(newUser);

        // then
        assertThat(savedUser).isNotNull(); // 널이면 안됨 //널이아니면 맞는거야 // 널이면 틀린거
    }

}