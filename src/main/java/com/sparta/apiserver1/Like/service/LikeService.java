package com.sparta.apiserver1.Like.service;

import com.sparta.apiserver1.Comment.entity.Comment;
import com.sparta.apiserver1.Post.entity.Post;
import com.sparta.apiserver1.User.entity.User;


public interface LikeService {


    void likePost(Long postId, User user);

    void deleteLikePost(Long postId, User user);

    void likeComment(Long commentId, User user);

    void deleteLikeComment(Long commentId, User user);

    Comment findComment(long id);

    Post findPost(long id);
}