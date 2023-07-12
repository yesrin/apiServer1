package com.sparta.apiserver1.Like.service;

import com.sparta.apiserver1.Comment.entity.Comment;
import com.sparta.apiserver1.Comment.repository.CommentRepository;
import com.sparta.apiserver1.Like.entity.Like;
import com.sparta.apiserver1.Like.repository.LikeRepository;
import com.sparta.apiserver1.Post.entity.Post;
import com.sparta.apiserver1.Post.repository.PostRepository;
import com.sparta.apiserver1.User.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void likePost(Long postId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        Optional<Like> likeExist = likeRepository.findByUserIdAndPostId(user.getId(), postId);

        likeExist.ifPresentOrElse(
                like -> { // 값이 있으면 삭제하고 count-1
                    likeRepository.delete(like);
                    post.disLike();
                },
                () -> { // 값이 없으면 추가하고 count+1
                    Like like = new Like(user, post);
                    likeRepository.save(like);
                    post.like();
                }
        );
    }

    @Transactional
    public void likeComment(Long postId, Long commentId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지 않습니다"));

        Optional<Like> likeExist = likeRepository.findByUserIdAndPostIdAndCommentId(user.getId(), postId, commentId);

        likeExist.ifPresentOrElse(
                like -> {
                    likeRepository.delete(like);
                    comment.disLike();
                },
                () -> {
                    Like like = new Like(user, post, comment);
                    likeRepository.save(like);
                    comment.like();
                }
        );
    }
}