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
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "contents", nullable = false, length = 500)
    private String contents;

    @Column(name="username",nullable = false)
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Post post;



    public Comment(CommentRequestDto requestDto,Post post){
        this.contents=requestDto.getContents();
        this.username=post.getUser().getUsername();
    }

}
