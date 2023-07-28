package com.sparta.apiserver1.Post.repository;

import com.sparta.apiserver1.Post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryQuery {
    Page<Post> search(String keyword, Pageable pageable);
}
