package com.sparta.apiserver1.Post.service;

import com.sparta.apiserver1.Post.dto.PostRequestDto;
import com.sparta.apiserver1.Post.dto.PostResponseDto;
import com.sparta.apiserver1.User.entity.User;

import java.util.List;


public interface PostSerive {

    List<PostResponseDto> getAllPostAndComment();

    Long deletePost(Long id, User user);

    PostResponseDto addPost(PostRequestDto requestDto, User user);


    PostResponseDto updatePost(Long id, PostRequestDto requestDto, User user);
}
