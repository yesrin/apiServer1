//package com.sparta.apiserver1.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sparta.apiserver1.Post.dto.PostRequestDto;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class PostsControllerTest {
//
//    @Autowired
//    ObjectMapper mapper;
//
//    @Autowired
//    MockMvc mvc;
//
//    private static final String BASE_URL = "/api";
//
//    @Test
//    @DisplayName("저장 컨트롤러 테스트")
//    void save_test() throws Exception {
//
//        //given
//        String title = "Test title";
//        String contents = "Test content";
//        //when
//        /**
//         * Object를 JSON으로 변환
//         * */
//
//        String body = mapper.writeValueAsString(
//
//                PostRequestDto.builder().title(title).contents(contents).build()
//        );
//
//        //then
//        mvc.perform(post(BASE_URL + "/post")
//                        //.header("Authorization", )
//                        .content(body) //HTTP Body에 데이터를 담는다
//                        .contentType(MediaType.APPLICATION_JSON) //보내는 데이터의 타입을 명시
//                )
//                .andExpect(status().isOk())
//                .andExpect(content().string("1"));
//    }
//}