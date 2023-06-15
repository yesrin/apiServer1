package com.sparta.apiserver1.dto;

import com.sparta.apiserver1.entity.Blog;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BlogResponseDto {
    private Long id;
    private String title;
    private String username;
    private String password;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public BlogResponseDto(Blog blog) {
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.username = blog.getUsername();
        this.password= blog.getPassword();
        this.contents = blog.getContents();
        this.createdAt=blog.getCreatedAt();
        this.modifiedAt=blog.getModifiedAt();
    }
}
