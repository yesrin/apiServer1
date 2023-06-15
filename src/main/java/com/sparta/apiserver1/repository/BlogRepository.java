package com.sparta.apiserver1.repository;

import com.sparta.apiserver1.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Long> {
    List<Blog> findAllByOrderByCreatedAtDesc();
}

