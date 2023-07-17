package com.sparta.apiserver1.Like.controller;

import com.sparta.apiserver1.Common.security.UserDetailsImpl;
import com.sparta.apiserver1.Like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/like/{postId}")
    public void likePost(@PathVariable Long postId,@AuthenticationPrincipal UserDetailsImpl userDetails){
        likeService.likePost(postId,userDetails.getUser());
    }

    @DeleteMapping("/like/{postId}")
    public void deleteLikePost(@PathVariable Long postId,@AuthenticationPrincipal UserDetailsImpl userDetails){
        likeService.deleteLikePost(postId,userDetails.getUser());
    }


    @PostMapping("/like/{postId}/{commentId}")
    public void likeComment(@PathVariable Long commentId,
                            @AuthenticationPrincipal UserDetailsImpl userDetails){

        likeService.likeComment(commentId,userDetails.getUser());

    }
    @DeleteMapping("/like/{postId}/{commentId}")
    public void deleteLikeComment(@PathVariable Long commentId,
                                  @AuthenticationPrincipal UserDetailsImpl userDetails){

        likeService.deleteLikeComment(commentId,userDetails.getUser());

    }
}
