package com.sparta.apiserver1.Like.repository;

import com.sparta.apiserver1.Comment.entity.Comment;
import com.sparta.apiserver1.Like.entity.CommentLike;
import com.sparta.apiserver1.Like.entity.PostLike;
import com.sparta.apiserver1.User.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike,Long> {
    boolean existsByUserAndComment(User user, Comment comment);

    Optional<CommentLike> findByUserAndComment(User user, Comment comment);
}
