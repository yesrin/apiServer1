package com.sparta.apiserver1.Post.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.apiserver1.Common.security.UserDetailsImpl;
import com.sparta.apiserver1.Post.dto.PostRequestDto;
import com.sparta.apiserver1.Post.dto.PostResponseDto;
import com.sparta.apiserver1.Post.entity.Post;
import com.sparta.apiserver1.Post.repository.PostRepository;
import com.sparta.apiserver1.User.entity.User;
import com.sparta.apiserver1.User.entity.UserRoleEnum;
import com.sparta.apiserver1.User.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.security.Principal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

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

    //    @Autowired
//    private ObjectMapper objectMapper;
    private void mockUserSetup() {
        // Mock 테스트 유져 생성
        String username = "sollertia4351";
        String password = "robbie1234";
        UserRoleEnum role = UserRoleEnum.USER;
        User testUser = new User(username, password, role);
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
//        //given
//        String title = "Test title";
//        String contents = "Test content";
//
//        PostRequestDto requestDto = new PostRequestDto(title, contents);
//        requestDto.setTitle(title);
//        requestDto.setContents(contents);
//
//        PostServiceImpl postService = new PostServiceImpl(postRepository, messageSource);
//
//        String username = "sollertia4351";
//        String password = "robbie1234";
//        UserRoleEnum role = UserRoleEnum.USER;
//        User user = new User(username, password, role);
//
//        Post post=new Post(requestDto,user);
//
//
//        //when 실제로 수행
//        PostResponseDto result = postService.addPost(requestDto, user);
//
//        //then
//        assertEquals(title, result.getTitle());
//        assertEquals(contents, result.getContents());
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

        String username = "sollertia4351";
        String password = "robbie1234";
        UserRoleEnum role = UserRoleEnum.USER;

        User user = new User(username, password, role);
        Post post=new Post(requestDto,user);

        PostServiceImpl postService = new PostServiceImpl(postRepository, messageSource);

        given(postRepository.findById(postId)).willReturn(Optional.of(post));

        //when 실제로 수행
        PostResponseDto result = postService.updatePost(postId, requestDto, user);

        //then
        assertEquals(title, result.getTitle());
        assertEquals(contents, result.getContents());
    }

    @Test
    void searchPost() {
    }
}