package com.sparta.apiserver1.repository;

import com.sparta.apiserver1.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
