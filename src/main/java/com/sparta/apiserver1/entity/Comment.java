package com.sparta.apiserver1.entity;

import com.sparta.apiserver1.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="commnt")
@NoArgsConstructor
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contents", nullable = false, length = 500)
    private String contents;

    @Column(name="username",nullable = false)
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Post post;

    @Column(name="post_number",nullable = false)
    private Long postNumber;



    public Comment(Long postId,CommentRequestDto requestDto,User user){
        this.contents=requestDto.getContents();
        this.username=user.getUsername();
        this.postNumber =postId;
    }

}
