package com.sparta.apiserver1.Post.service;

import com.sparta.apiserver1.Common.security.UserDetailsImpl;
import com.sparta.apiserver1.Post.dto.PostRequestDto;
import com.sparta.apiserver1.Post.dto.PostResponseDto;
import com.sparta.apiserver1.Post.entity.Post;
import com.sparta.apiserver1.Post.repository.PostRepository;
import com.sparta.apiserver1.User.entity.User;
import com.sparta.apiserver1.User.entity.UserRoleEnum;
import com.sparta.apiserver1.User.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.security.Principal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) //@Mock 사용을 위해 선언
class PostSeriveTest {
    private Principal mockPrincipal;
    @Mock
    PostRepository postRepository;
    @Mock
    UserDetailsImpl userDetails;
    @Mock
    UserRepository userRepository;
    @Mock
    MessageSource messageSource;

    User testUser;

    @BeforeEach
    private void mockUserSetup() {
        // Mock 테스트 유져 생성
        String username = "sollertia4351";
        String password = "robbie1234";
        UserRoleEnum role = UserRoleEnum.USER;
        testUser = new User(username, password, role);
        UserDetailsImpl testUserDetails = new UserDetailsImpl(testUser);
        mockPrincipal = new UsernamePasswordAuthenticationToken(testUserDetails, "", testUserDetails.getAuthorities());
    }

    @Test
    void getAllPostAndComment() {
    }

    @Test
    void deletePost() {
    }

    @Test
    void addPost() {
        //given
        String title = "Test title";
        String contents = "Test content";

        PostRequestDto requestDto = new PostRequestDto(title, contents);

        PostServiceImpl postService = new PostServiceImpl(postRepository, messageSource);

        Post post = new Post(requestDto, testUser);

        given(postRepository.save(any())).willReturn(post); //save에 아무거나 넣어도 post 반환하겠다

        //when 실제로 수행
        PostResponseDto result = postService.addPost(requestDto, testUser);

        //then
        //행위검증으로는 verify()를 사용하기도 함
        then(postRepository).should(times(1)).save(any()); //한번호출

        // 리턴값은 상태검증
        assertEquals(title, result.getTitle());
    }

    @Test
    @DisplayName("게시글 업데이트")
    void updatePost() {
        //given
        Long postId = 3L;
        String title = "Test title";
        String contents = "Test content";

        PostRequestDto requestDto = new PostRequestDto(title, contents);
        requestDto.setTitle(title);
        requestDto.setContents(contents);

        Post post = new Post(requestDto, testUser);
        PostServiceImpl postService = new PostServiceImpl(postRepository, messageSource);

        given(postRepository.findById(postId)).willReturn(Optional.of(post));

        //when 실제로 수행
        PostResponseDto result = postService.updatePost(postId, requestDto, testUser);

        //then
        assertEquals(title, result.getTitle());
        assertEquals(contents, result.getContents());
    }

    @Test
    void searchPost() {
    }
}