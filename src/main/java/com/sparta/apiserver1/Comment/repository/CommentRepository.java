package com.sparta.apiserver1.Comment.repository;

import com.sparta.apiserver1.Comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
