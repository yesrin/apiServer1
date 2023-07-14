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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeService {

    private final PostLikeRepository postLikeRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public void likePost(Long postId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        Optional<PostLike> findPostLike = postLikeRepository.findByUserIdAndPostId(user.getId(), postId);

        findPostLike.ifPresentOrElse(
                postlike -> { // 값이 있으면 삭제하고 count-1
                    postLikeRepository.delete(postlike);
                    post.disLike();
                    log.info("게시글 좋아요 취소");
                },
                () -> { // 값이 없으면 추가하고 count+1
                    PostLike postlike = new PostLike(user, post);
                    postLikeRepository.save(postlike);
                    post.like();
                    log.info("게시글 좋아요 추가");
                }
        );
    }

    @Transactional
    public void likeComment(Long postId, Long commentId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다"));

        Optional<CommentLike> findCommentLike = commentLikeRepository.findByUserIdAndPostIdAndCommentId(user.getId(),postId,commentId);

        findCommentLike.ifPresentOrElse(
                commentLike -> {
                    commentLikeRepository.delete(commentLike);
                    comment.disLike();
                    log.info("댓글 좋아요 취소");
                },
                () -> {
                    CommentLike commentLike = new CommentLike(user, post, comment);
                    commentLikeRepository.save(commentLike);
                    comment.like();
                    log.info("댓글 좋아요 추가");
                }
        );
    }
}