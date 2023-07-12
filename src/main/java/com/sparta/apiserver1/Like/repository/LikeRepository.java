package com.sparta.apiserver1.Like.repository;

import com.sparta.apiserver1.Like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Long> {
    Optional<Like> findByUserIdAndPostId(Long user_id, Long post_id);
    Optional<Like> findByUserIdAndPostIdAndCommentId(Long user_id, Long post_id,Long comment_id);
}
