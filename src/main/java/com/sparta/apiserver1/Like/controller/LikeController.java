package com.sparta.apiserver1.Like.controller;

import com.sparta.apiserver1.Common.security.UserDetailsImpl;
import com.sparta.apiserver1.Like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/like/{postId}")
    public String likePost(@PathVariable Long postId,@AuthenticationPrincipal UserDetailsImpl userDetails){
        return likeService.likePost(postId,userDetails.getUser());
    }



    @PostMapping("/like/{postId}/{commentId}")
    public Boolean likeComment(@PathVariable Long postId,
                            @PathVariable Long commentId,
                            @AuthenticationPrincipal UserDetailsImpl userDetails){

        return likeService.likeComment(postId,commentId,userDetails.getUser());

    }
}
