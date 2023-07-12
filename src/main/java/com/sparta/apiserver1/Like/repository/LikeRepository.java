package com.sparta.apiserver1.Like.repository;

import com.sparta.apiserver1.Like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {
    Like findByUserIdAndPostId(Long user_id, Long post_id);
}
