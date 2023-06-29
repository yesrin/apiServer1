package com.sparta.apiserver1.controller;

import com.sparta.apiserver1.dto.PostRequestDto;
import com.sparta.apiserver1.dto.Post;
import com.sparta.apiserver1.dto.PostResponseDto;
import com.sparta.apiserver1.security.UserDetailsImpl;
import com.sparta.apiserver1.service.PostSerive;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    private final PostSerive postSerive;

    public PostController(PostSerive postSerive) {
        this.postSerive = postSerive;
    }

    @GetMapping("/blog")
    public List<PostResponseDto> getBlogALL() {
        return postSerive.getBlogALL();
    }

    @GetMapping("/blog/{id}")
    public PostResponseDto getBlogByID(@PathVariable Long id){
        return postSerive.getBlogByID(id);
    }

    @PutMapping("/blog/{id}")
    public boolean updateBlog(@PathVariable Long id,
                              @RequestBody PostRequestDto requestDto,
                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postSerive.updateBlog(id, requestDto, userDetails.getUser());
    }
    @DeleteMapping("/blog/{id}")
    public Long deleteBlog(@PathVariable Long id) {
        return postSerive.deleteBlog(id);
    }

    @PostMapping("/blog")
    public PostResponseDto addPost (@RequestBody PostRequestDto requestDto,
                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postSerive.addPost(requestDto, userDetails.getUser());
    }



}

