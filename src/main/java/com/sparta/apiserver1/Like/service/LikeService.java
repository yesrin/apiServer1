package com.sparta.apiserver1.Like.service;

import com.sparta.apiserver1.Comment.entity.Comment;
import com.sparta.apiserver1.Comment.repository.CommentRepository;
import com.sparta.apiserver1.Like.entity.CommentLike;
import com.sparta.apiserver1.Like.entity.PostLike;
import com.sparta.apiserver1.Like.repository.CommentLikeRepository;
import com.sparta.apiserver1.Like.repository.PostLikeRepository;
import com.sparta.apiserver1.Post.entity.Post;
import com.sparta.apiserver1.Post.repository.PostRepository;
import com.sparta.apiserver1.User.entity.User;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeService {

    private final PostLikeRepository postLikeRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final MessageSource messageSource;

    @Transactional
    public void likePost(Long postId, User user) {
        Post post = findPost(postId);

        // 아래 조건 코드와 동일
        // if (postLikeRepository.findByUserAndPost(user, post).isPresent()) {
        if (postLikeRepository.existsByUserAndPost(user, post)) {
            throw new DuplicateRequestException(
                    messageSource.getMessage(
                            "already.liked",
                            null,
                            "already liked Comment",
                            Locale.getDefault()
                    ));
        } else {
            PostLike postLike = new PostLike(user, post);
            postLikeRepository.save(postLike);
        }
    }

    public void deleteLikePost(Long postId, User user) {
        Post post = findPost(postId);
        Optional<PostLike> postLikeOptional = postLikeRepository.findByUserAndPost(user, post);
        if (postLikeOptional.isPresent()) {
            postLikeRepository.delete(postLikeOptional.get());
        } else {
            throw new IllegalArgumentException(
                    messageSource.getMessage(
                            "no.likes.to.cancel",
                            null,
                            "no likes to cancel",
                            Locale.getDefault()
                    )
            );
        }
    }

    @Transactional
    public void likeComment(Long commentId, User user) {
        Comment comment = findComment(commentId);

        if (commentLikeRepository.existsByUserAndComment(user, comment)) {
            throw new DuplicateRequestException(
                    messageSource.getMessage(
                            "already.liked",
                            null,
                            "already liked Comment",
                            Locale.getDefault()
                    ));
        } else {
            CommentLike commentLike = new CommentLike(user, comment);
            commentLikeRepository.save(commentLike);
        }
    }

    public void deleteLikeComment(Long commentId, User user) {
        Comment comment = findComment(commentId);
        Optional<CommentLike> commentLikeOptional = commentLikeRepository.findByUserAndComment(user, comment);
        if (commentLikeOptional.isPresent()) {
            commentLikeRepository.delete(commentLikeOptional.get());
        } else {
            throw new IllegalArgumentException(
                    messageSource.getMessage(
                            "no.likes.to.cancel",
                            null,
                            "no likes to cancel",
                            Locale.getDefault()
                    ));
        }
    }

    public Comment findComment(long id) {
        return commentRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException(
                        messageSource.getMessage(
                                "comment.not.exist",
                                null,
                                "comment do not exist",
                                Locale.getDefault()
                        )
                ));
    }

    public Post findPost(long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException(
                        messageSource.getMessage(
                                "post.not.exist",
                                null,
                                "post does not exist",
                                Locale.getDefault()
                        )
                ));
    }
}