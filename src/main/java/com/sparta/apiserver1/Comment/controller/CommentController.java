package com.sparta.apiserver1.Comment.controller;

import com.sparta.apiserver1.Comment.dto.CommentRequestDto;
import com.sparta.apiserver1.Comment.dto.CommentResponseDto;
import com.sparta.apiserver1.Common.security.UserDetailsImpl;
import com.sparta.apiserver1.Comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/comment/{postId}")
    public CommentResponseDto addComment(@PathVariable Long postId,
                                         @RequestBody CommentRequestDto requestDto,
                                         @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return commentService.addComment(postId,requestDto,userDetails.getUser());
    }

    @PutMapping("/comment/{postId}/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long postId,
                                            @PathVariable Long commentId,
                                            @RequestBody CommentRequestDto requestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.updateComment(postId,commentId,requestDto,userDetails.getUser());
    }

    @DeleteMapping("/comment/{postId}/{commentId}")
    public CommentResponseDto deleteCommit(@PathVariable Long postId,
                                     @PathVariable Long commentId,
                                     @AuthenticationPrincipal UserDetailsImpl userDetails){
        return commentService.deleteCommit(postId,commentId,userDetails.getUser());
    }
}
