package com.sparta.apiserver1.Post.repository;

import com.sparta.apiserver1.Post.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomPostRepository {
//    List<Post> findAllByOrderByCreatedAtDesc();
    List<Post> search(String keyword);
}
