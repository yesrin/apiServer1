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
@Table(name="comment_like")
@NoArgsConstructor
public class CommentLike extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comment comment;

    public CommentLike(User user,Post post, Comment comment) {
        this.user = user;
        this.post=post;
        this.comment = comment;
    }
}
