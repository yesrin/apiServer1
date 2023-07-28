package com.sparta.apiserver1.Post.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.apiserver1.Post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.Objects;

import static com.sparta.apiserver1.Post.entity.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryQueryImpl implements PostRepositoryQuery {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Post> search(String keyword, Pageable pageable) {

        var query = jpaQueryFactory.select(post)
                .from(post)
                .where(
                        titleContains(keyword)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        var posts = query.fetch();

        long totalSize = jpaQueryFactory.select(Wildcard.count)
                .from(post)
                .where(titleContains(keyword))
                .fetch().get(0);

        return PageableExecutionUtils.getPage(posts, pageable, () -> totalSize);

    }

    private static BooleanExpression titleContains(String keyword) {
        return Objects.nonNull(keyword) ? post.title.contains(keyword) : null;
    }
}
