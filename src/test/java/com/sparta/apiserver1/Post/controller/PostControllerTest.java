package com.sparta.apiserver1.Post.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.sparta.apiserver1.Common.config.WebSecurityConfig;
import com.sparta.apiserver1.Common.security.UserDetailsImpl;
import com.sparta.apiserver1.MockSpringSecurityFilter;
import com.sparta.apiserver1.Post.dto.PostRequestDto;
import com.sparta.apiserver1.Post.service.PostSerive;
import com.sparta.apiserver1.User.controller.UserController;
import com.sparta.apiserver1.User.entity.User;
import com.sparta.apiserver1.User.entity.UserRoleEnum;
import com.sparta.apiserver1.User.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        controllers = {UserController.class, PostController.class},
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = WebSecurityConfig.class
                )
        }
)
class PostControllerTest {

    private MockMvc mvc;
    private Principal mockPrincipal;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    UserService userService;

    @MockBean
    PostSerive postSerive;

    private void mockUserSetup() {
        // Mock 테스트 유져 생성
        String username = "sollertia4351";
        String password = "robbie1234";
        UserRoleEnum role = UserRoleEnum.USER;
        User testUser = new User(username, password, role);
        UserDetailsImpl testUserDetails = new UserDetailsImpl(testUser);
        mockPrincipal = new UsernamePasswordAuthenticationToken(testUserDetails, "", testUserDetails.getAuthorities());
    }

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity(new MockSpringSecurityFilter()))
                .build();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getAllPostAndComment() {
    }

    @Test
    void searchPost() {
    }

    @Test
    void updatePost() {
    }

    @Test
    void deletePost() {
    }

    @Test
    void addPost() throws Exception {
        // given
        this.mockUserSetup();
        String title = "게시물 타이틀";
        String contents = "게시물 내용";
        PostRequestDto requestDto = new PostRequestDto(
                title,
                contents
        );

        String postInfo = objectMapper.writeValueAsString(requestDto);

        // when - then
        mvc.perform(post("/api/post")
                        .content(postInfo)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .principal(mockPrincipal)
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
}
