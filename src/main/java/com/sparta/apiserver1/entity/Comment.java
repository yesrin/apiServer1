package com.sparta.apiserver1.entity;

import com.sparta.apiserver1.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name="like_count")
    private int likeCount;

    @OneToMany(mappedBy = "comment",cascade = CascadeType.ALL)
    private List<Like> likes=new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Post post;


    public Comment(CommentRequestDto requestDto,User user,Post post){
        this.contents=requestDto.getContents();
        this.username=user.getUsername();
        this.user=user;
        this.post=post;
    }

    public void checkUsername(String username) {
        if(!this.username.equals(username)) {
            throw new IllegalArgumentException("작성자가 일치하지 않습니다.");
        }
    }

    public void update(CommentRequestDto requestDto) {
        this.contents=requestDto.getContents();
    }
}
