package com.sparta.apiserver1.controller;


import com.sparta.apiserver1.dto.PostRequestDto;
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

    @GetMapping("/post")
    public List<PostResponseDto> getPostALL() {
        return postSerive.getPostALL();
    }

    @GetMapping("/post/{id}")
    public PostResponseDto getPostByID(@PathVariable Long id){
        return postSerive.getPostByID(id);
    }

    @PutMapping("/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id,
                              @RequestBody PostRequestDto requestDto,
                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postSerive.updatePost(id, requestDto, userDetails.getUser());
    }
    @DeleteMapping("/post/{id}")
    public Long deletePost(@PathVariable Long id) {
        return postSerive.deletePost(id);
    }

    @PostMapping("/post")
    public PostResponseDto addPost (@RequestBody PostRequestDto requestDto,
                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postSerive.addPost(requestDto, userDetails.getUser());
    }



}

