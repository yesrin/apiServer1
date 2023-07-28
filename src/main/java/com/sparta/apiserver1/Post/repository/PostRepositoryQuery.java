package com.sparta.apiserver1.Post.repository;

import com.sparta.apiserver1.Post.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PostRepositoryQuery {
    List<Post> search(String keyword);
}
