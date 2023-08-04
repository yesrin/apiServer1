package com.sparta.apiserver1.Post.service;

import com.sparta.apiserver1.Post.dto.PostRequestDto;
import com.sparta.apiserver1.Post.dto.PostResponseDto;
import com.sparta.apiserver1.User.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface PostSerive {

    List<PostResponseDto> getAllPostAndComment();

    Long deletePost(Long id, User user);

    PostResponseDto addPost(PostRequestDto requestDto, User user,List<MultipartFile> image) throws IOException;


    PostResponseDto updatePost(Long id, PostRequestDto requestDto, User user,List<MultipartFile> image) throws IOException;

    List<PostResponseDto> searchPost(String keyword, Pageable pageable);
}
