package com.sparta.apiserver1.Comment.dto;

import com.sparta.apiserver1.Comment.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long id;
    private Long post_id;
    private String contents;
    private String username;
    private Long likeCount;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.post_id=comment.getPost().getId();
        this.contents = comment.getContents();
        this.username = comment.getUsername();
        this.likeCount=comment.getLikeCount();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }

}
