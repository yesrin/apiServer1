package com.sparta.apiserver1.Comment.service;


import com.sparta.apiserver1.Comment.dto.CommentRequestDto;
import com.sparta.apiserver1.Comment.dto.CommentResponseDto;
import com.sparta.apiserver1.Comment.entity.Comment;
import com.sparta.apiserver1.Comment.repository.CommentRepository;
import com.sparta.apiserver1.Post.entity.Post;
import com.sparta.apiserver1.Post.repository.PostRepository;
import com.sparta.apiserver1.User.entity.User;
import com.sparta.apiserver1.User.entity.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MessageSource messageSource;

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
        //요청자(user) 가 같은지 체크
        if (!comment.getUsername().equals(user.getUsername())) {
            throw new IllegalArgumentException(
                    messageSource.getMessage(
                            "only.writer.update",
                            null ,
                            "only writer can update",
                            Locale.getDefault()
                    )
            );
        }
        comment.update(requestDto);
        return new CommentResponseDto(comment);

    }
    public CommentResponseDto deleteCommit(Long postId, Long commentId, User user) {
        findPost(postId);
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("선택한 댓글이 존재하지 않습니다."));

        //요청자(user) 가 같은지 체크
        if (!comment.getUsername().equals(user.getUsername())) {
            throw new IllegalArgumentException(
                    messageSource.getMessage(
                            "only.writer.delete",
                            null ,
                            "only writer can delete",
                            Locale.getDefault()
                    )
            );
        }
        commentRepository.delete(comment);

        return new CommentResponseDto(comment);
    }

    private Post findPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시물이 존재하지 않습니다"));
    }


}



