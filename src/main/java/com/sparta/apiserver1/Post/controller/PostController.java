package com.sparta.apiserver1.Post.controller;


import com.sparta.apiserver1.Common.security.UserDetailsImpl;
import com.sparta.apiserver1.Post.dto.PostRequestDto;
import com.sparta.apiserver1.Post.dto.PostResponseDto;
import com.sparta.apiserver1.Post.service.PostSerive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public List<PostResponseDto> searchPost(String keyword, Pageable pageable) {
        return postSerive.searchPost(keyword, pageable);
    }

    @PutMapping(value = "/post/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public PostResponseDto updatePost(@PathVariable Long id,
                                      @RequestPart PostRequestDto requestDto,
                                      @RequestPart List<MultipartFile> image,
                                      @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        return postSerive.updatePost(id, requestDto, userDetails.getUser(), image);
    }

    @DeleteMapping("/post/{id}")
    public Long deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postSerive.deletePost(id, userDetails.getUser());
    }

    @PostMapping(value = "/post", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public PostResponseDto addPost(@RequestPart PostRequestDto requestDto,
                                   @RequestPart List<MultipartFile> image,
                                   @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
        return postSerive.addPost(requestDto, userDetails.getUser(), image);
    }


}

