package com.sparta.apiserver1.Post.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.apiserver1.Post.entity.Post;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.sparta.apiserver1.Post.entity.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryQueryImpl implements PostRepositoryQuery {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> search(String keyword) {

        var query = jpaQueryFactory.select(post)
                .from(post)
                .where(
                        post.title.contains(keyword)
                );

        var posts = query.fetch();

        return posts;
    }
}
