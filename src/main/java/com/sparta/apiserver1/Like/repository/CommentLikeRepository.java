package com.sparta.apiserver1.Like.repository;

import com.sparta.apiserver1.Like.entity.CommentLike;
import com.sparta.apiserver1.Like.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike,Long> {
    Optional<CommentLike> findByUserIdAndPostIdAndCommentId(Long user_id,Long post, Long comment_id);
}
