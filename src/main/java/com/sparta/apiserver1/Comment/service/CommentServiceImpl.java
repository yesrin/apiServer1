package com.sparta.apiserver1.Comment.service;

import com.sparta.apiserver1.Comment.dto.CommentRequestDto;
import com.sparta.apiserver1.Comment.dto.CommentResponseDto;
import com.sparta.apiserver1.Comment.entity.Comment;
import com.sparta.apiserver1.Comment.repository.CommentRepository;
import com.sparta.apiserver1.Post.entity.Post;
import com.sparta.apiserver1.Post.repository.PostRepository;
import com.sparta.apiserver1.User.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MessageSource messageSource;


    @Override
    public List<CommentResponseDto> selecteAllComment() {
        return commentRepository.findAllByOrderByCreatedAtDesc().stream().map(CommentResponseDto::new).toList();
    }

    @Override
    @Transactional
    public CommentResponseDto addComment(Long postId, CommentRequestDto requestDto, User user) {
        Post post = findPost(postId);
        Comment comment = new Comment(requestDto, user, post);
        Comment saveComment = commentRepository.save(comment);

        return new CommentResponseDto(saveComment);
    }

    @Override
    @Transactional
    public CommentResponseDto updateComment(Long postId, Long commentId, CommentRequestDto requestDto, User user) {
        findPost(postId);

        //댓글이 존재하는지 확인
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new IllegalArgumentException(
                        messageSource.getMessage(
                                "comment.not.exist",
                                null,
                                "comment do not exist",
                                Locale.getDefault()
                        )
                )
        );

        //요청자(user) 가 같은지 체크
        if (!comment.getUsername().equals(user.getUsername())) {
            throw new IllegalArgumentException(
                    messageSource.getMessage(
                            "only.writer.update",
                            null,
                            "only writer can update",
                            Locale.getDefault()
                    )
            );
        }
        comment.update(requestDto);
        return new CommentResponseDto(comment);

    }

    @Override
    public CommentResponseDto deleteCommit(Long postId, Long commentId, User user) {
        findPost(postId);
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException(
                messageSource.getMessage(
                        "comment.not.exist",
                        null,
                        "comment do not exist",
                        Locale.getDefault()
                )));

        //요청자(user) 가 같은지 체크
        if (!comment.getUsername().equals(user.getUsername())) {
            throw new IllegalArgumentException(
                    messageSource.getMessage(
                            "only.writer.delete",
                            null,
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
                new IllegalArgumentException(
                        messageSource.getMessage(
                                "post.not.exist",
                                null,
                                "post does not exist",
                                Locale.getDefault()
                        )));
    }
}
