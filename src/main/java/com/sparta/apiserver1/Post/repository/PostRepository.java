package com.sparta.apiserver1.Post.repository;

import com.sparta.apiserver1.Post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryQuery {
    List<Post> findAllByOrderByCreatedAtDesc();
}

