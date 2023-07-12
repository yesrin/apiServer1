package com.sparta.apiserver1.Post.dto;

import com.sparta.apiserver1.Post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String contents;
    private Long likeCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.likeCount= post.getLikeCount();
        this.createdAt= post.getCreatedAt();
        this.modifiedAt= post.getModifiedAt();
    }
}
