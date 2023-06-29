package com.sparta.apiserver1.service;


import com.sparta.apiserver1.dto.CommentRequestDto;
import com.sparta.apiserver1.dto.CommentResponseDto;
import com.sparta.apiserver1.dto.PostResponseDto;
import com.sparta.apiserver1.entity.Comment;
import com.sparta.apiserver1.entity.Post;
import com.sparta.apiserver1.entity.User;
import com.sparta.apiserver1.repository.CommentRepository;
import com.sparta.apiserver1.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    @Transactional
    public CommentResponseDto addComment(Long postId,CommentRequestDto requestDto, User user) {
        findPost(postId);
        Comment comment= new Comment(postId,requestDto,user);
        Comment saveComment= commentRepository.save(comment);

        return new CommentResponseDto(saveComment);
    }

    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시물이 존재하지 않습니다"));
    };
}



