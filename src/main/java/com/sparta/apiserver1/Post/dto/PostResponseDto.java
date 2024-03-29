package com.sparta.apiserver1.Post.dto;

import com.sparta.apiserver1.Post.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String imageUrl;
    private Integer likeCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.imageUrl=post.getImageUrl();
        this.likeCount= post.getPostLikes().size();
        this.createdAt= post.getCreatedAt();
        this.modifiedAt= post.getModifiedAt();
    }
}
