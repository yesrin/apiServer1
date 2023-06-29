package com.sparta.apiserver1.controller;

import com.sparta.apiserver1.dto.CommentRequestDto;
import com.sparta.apiserver1.dto.CommentResponseDto;
import com.sparta.apiserver1.security.UserDetailsImpl;
import com.sparta.apiserver1.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Provider;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comment")
    public CommentResponseDto addComment(@RequestBody CommentRequestDto requestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails){

        return commentService.addComment(requestDto,userDetails.getUser());
    }
}
