package com.sparta.apiserver1.Post.service;

import com.sparta.apiserver1.Common.image.ImageUploader;
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
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

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
    @Mock
    ImageUploader imageUploader;
    User testUser;
    private Object MultipartFile;

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
    void addPost() throws IOException {
        //given
        String title = "Test title";
        String contents = "Test content";
        String imageUrl = "http://dsfadio";

        PostRequestDto requestDto = new PostRequestDto(title, contents, imageUrl);

        PostServiceImpl postService = new PostServiceImpl(postRepository, messageSource, imageUploader);

        Post post = new Post(requestDto, testUser);

        given(postRepository.save(any())).willReturn(post); //save에 아무거나 넣어도 post 반환하겠다

        List<MultipartFile> image = new ArrayList<>();

        //when 실제로 수행
        PostResponseDto result = postService.addPost(requestDto, testUser, image);

        //then
        //행위검증으로는 verify()를 사용하기도 함
        then(postRepository).should(times(1)).save(any()); //한번호출

        // 리턴값은 상태검증
        assertEquals(title, result.getTitle());
    }

    @Test
    @DisplayName("게시글 업데이트")
    void updatePost() throws IOException {
        //given
        Long postId = 3L;
        String title = "Test title";
        String contents = "Test content";
        String imageUrl = "http://dsfadio";

        PostRequestDto requestDto = new PostRequestDto(title, contents, imageUrl);
        requestDto.setTitle(title);
        requestDto.setContents(contents);

        Post post = new Post(requestDto, testUser);

        List<MultipartFile> image = new ArrayList<>();

        PostServiceImpl postService = new PostServiceImpl(postRepository, messageSource, imageUploader);

        given(postRepository.findById(postId)).willReturn(Optional.of(post));

        //when 실제로 수행
        PostResponseDto result = postService.updatePost(postId, requestDto, testUser, image);

        //then
        assertEquals(title, result.getTitle());
        assertEquals(contents, result.getContents());
    }

    @Test
    void searchPost() {
    }
}