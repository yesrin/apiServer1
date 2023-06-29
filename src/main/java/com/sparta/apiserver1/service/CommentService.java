package com.sparta.apiserver1.service;

import com.sparta.apiserver1.dto.CommentRequestDto;
import com.sparta.apiserver1.dto.CommentResponseDto;
import com.sparta.apiserver1.entity.User;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public class CommentService {
    public CommentResponseDto addComment(CommentRequestDto requestDto, User user) {

        return new CommentResponseDto();
    }
}
