package com.sparta.apiserver1.Post.controller;


import com.sparta.apiserver1.Post.dto.PostRequestDto;
import com.sparta.apiserver1.Post.dto.PostResponseDto;
import com.sparta.apiserver1.Common.security.UserDetailsImpl;
import com.sparta.apiserver1.Post.entity.Post;
import com.sparta.apiserver1.Post.service.PostSerive;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {
    private final PostSerive postSerive;

    @GetMapping("/post")
    public List<PostResponseDto> getAllPostAndComment() {
        return postSerive.getAllPostAndComment();
    }
    @GetMapping("/post/search")
    public List<Post> searchPost(String keyword) {
        return postSerive.searchPost(keyword);
    }
    @PutMapping("/post/{id}")
    public PostResponseDto updatePost(@PathVariable Long id,
                              @RequestBody PostRequestDto requestDto,
                              @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postSerive.updatePost(id, requestDto, userDetails.getUser());
    }
    @DeleteMapping("/post/{id}")
    public Long deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postSerive.deletePost(id,userDetails.getUser());
    }

    @PostMapping("/post")
    public PostResponseDto addPost (@RequestBody PostRequestDto requestDto,
                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postSerive.addPost(requestDto, userDetails.getUser());
    }



}

