package com.sparta.apiserver1.Comment.service;


import com.sparta.apiserver1.Comment.dto.CommentRequestDto;
import com.sparta.apiserver1.Comment.dto.CommentResponseDto;
import com.sparta.apiserver1.Comment.entity.Comment;
import com.sparta.apiserver1.Comment.repository.CommentRepository;
import com.sparta.apiserver1.Post.entity.Post;
import com.sparta.apiserver1.Post.repository.PostRepository;
import com.sparta.apiserver1.User.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public List<CommentResponseDto> selecteAllComment() {
        return commentRepository.findAllByOrderByCreatedAtDesc().stream().map(CommentResponseDto::new).toList();
    }


    @Transactional
    public CommentResponseDto addComment(Long postId, CommentRequestDto requestDto, User user) {
        Post post = findPost(postId);
        Comment comment = new Comment(requestDto, user, post);
        Comment saveComment = commentRepository.save(comment);

        return new CommentResponseDto(saveComment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long postId, Long commentId, CommentRequestDto requestDto, User user) {
        findPost(postId);
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("선택한 댓글이 존재하지 않습니다."));
        comment.checkUsername(user.getUsername()); // 지금 로그인한 사람이 댓글 작성한 사람이 맞는지 확인
        comment.update(requestDto);
        return new CommentResponseDto(comment);

    }
    public CommentResponseDto deleteCommit(Long postId, Long commentId, User user) {
        findPost(postId);
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("선택한 댓글이 존재하지 않습니다."));
        comment.checkUsername(user.getUsername());
        commentRepository.delete(comment);

        return new CommentResponseDto(comment);
    }

    private Post findPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시물이 존재하지 않습니다"));
    }

    ;


}



