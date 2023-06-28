package com.sparta.apiserver1.controller;

import com.sparta.apiserver1.dto.BlogRequestDto;
import com.sparta.apiserver1.dto.BlogResponseDto;
import com.sparta.apiserver1.entity.Blog;
import com.sparta.apiserver1.security.UserDetailsImpl;
import com.sparta.apiserver1.service.BlogService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

//    @PostMapping("/blog")
//    public BlogResponseDto createBlog(@RequestBody BlogRequestDto requestDto) {
//        return blogService.createBlog(requestDto);
//    }


    @GetMapping("/blog")
    public List<BlogResponseDto> getBlogALL() {
        return blogService.getBlogALL();
    }

    @GetMapping("/blog/{id}")
    public BlogResponseDto getBlogByID(@PathVariable Long id) {
        return blogService.getBlogByID(id);
    }



    @PostMapping("/blog/post")
    public BlogResponseDto addPost(@RequestBody BlogRequestDto requestDto,
                                   @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return blogService.addPost(requestDto, userDetails.getUser());
    }




}

