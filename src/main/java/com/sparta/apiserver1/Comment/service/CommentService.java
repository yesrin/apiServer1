package com.sparta.apiserver1.Comment.service;


import com.sparta.apiserver1.Comment.dto.CommentRequestDto;
import com.sparta.apiserver1.Comment.dto.CommentResponseDto;
import com.sparta.apiserver1.User.entity.User;

import java.util.List;


public interface CommentService {
    List<CommentResponseDto> selecteAllComment();

    CommentResponseDto addComment(Long postId, CommentRequestDto requestDto, User user);

    CommentResponseDto updateComment(Long postId, Long commentId, CommentRequestDto requestDto, User user);

    CommentResponseDto deleteCommit(Long postId, Long commentId, User user);

}



