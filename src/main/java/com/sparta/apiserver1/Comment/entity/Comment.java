package com.sparta.apiserver1.Comment.entity;

import com.sparta.apiserver1.Comment.dto.CommentRequestDto;
import com.sparta.apiserver1.Like.entity.Like;
import com.sparta.apiserver1.Post.entity.Post;
import com.sparta.apiserver1.Common.entity.Timestamped;
import com.sparta.apiserver1.User.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Entity
@Getter
@Setter
@Table(name="comment")
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
    private Long likeCount;

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

    public void update(CommentRequestDto requestDto) {
        this.contents=requestDto.getContents();
    }

    public void like(){
        this.likeCount=likeCount+1;
    }
    public void disLike(){
        this.likeCount=likeCount-1;
    }
}
