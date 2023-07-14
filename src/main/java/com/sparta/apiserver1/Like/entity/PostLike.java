package com.sparta.apiserver1.Like.entity;

import com.sparta.apiserver1.Comment.entity.Comment;
import com.sparta.apiserver1.Common.entity.Timestamped;
import com.sparta.apiserver1.Post.entity.Post;
import com.sparta.apiserver1.User.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="post_like")
@NoArgsConstructor
public class PostLike extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public PostLike(User user, Post post) {
        this.user = user;
        this.post = post;
    }
}