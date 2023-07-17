package com.sparta.apiserver1.Post.entity;

import com.sparta.apiserver1.Comment.entity.Comment;
import com.sparta.apiserver1.Like.entity.PostLike;
import com.sparta.apiserver1.Post.dto.PostRequestDto;
import com.sparta.apiserver1.Common.entity.Timestamped;
import com.sparta.apiserver1.User.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "post")
@NoArgsConstructor
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "contents", nullable = false, length = 500)
    private String contents;

    @Column(name="username", nullable = false)
    private String  username;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<PostLike> postLikes=new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<Comment> comments =new ArrayList<>();

    public Post(PostRequestDto requestDto, User user) {
        this.title=requestDto.getTitle();
        this.contents= requestDto.getContents();
        this.username= user.getUsername();
        this.user=user;
    }

    public void update(PostRequestDto requestDto) {
        this.title=requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
}
