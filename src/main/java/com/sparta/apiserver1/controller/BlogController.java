package com.sparta.apiserver1.controller;

import com.sparta.apiserver1.dto.BlogRequestDto;
import com.sparta.apiserver1.dto.BlogResponseDto;
import com.sparta.apiserver1.entity.Blog;
import com.sparta.apiserver1.service.BlogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/blog")
    public BlogResponseDto createMemo(@RequestBody BlogRequestDto requestDto) {
        return blogService.createBlog(requestDto);
    }


    @GetMapping("/blogALL")
    public List<BlogResponseDto> getBlogALL() {
        return blogService.getBlogALL();
    }

    @GetMapping("/blog/{id}")
    public BlogResponseDto getBlogByID(@PathVariable Long id){
        return blogService.getBlogByID(id);
    }

    @PutMapping("/blog/{id}")
    public Long updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {
        return blogService.updateBlog(id, requestDto);
    }
    @DeleteMapping("/blog/{id}")
    public Long deleteBlog(@PathVariable Long id) {
        return blogService.deleteBlog(id);
    }


}

