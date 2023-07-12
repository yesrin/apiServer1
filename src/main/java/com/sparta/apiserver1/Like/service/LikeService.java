package com.sparta.apiserver1.Like.service;

import com.sparta.apiserver1.Comment.entity.Comment;
import com.sparta.apiserver1.Comment.repository.CommentRepository;
import com.sparta.apiserver1.Like.entity.Like;
import com.sparta.apiserver1.Like.repository.LikeRepository;
import com.sparta.apiserver1.Post.entity.Post;
import com.sparta.apiserver1.Post.repository.PostRepository;
import com.sparta.apiserver1.User.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public String likePost(Long postId, User user) {
        Post post=postRepository.findById(postId).orElseThrow(()-> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        Like like= new Like(user,post);

        Like likeExist= likeRepository.findByUserIdAndPostId(user.getId(),postId);
        if(likeExist==null){
            likeRepository.save(like);
            post.like();
            return "게시글 좋아요";
        }else {
            likeRepository.delete(likeExist);
            post.disLike();
            return "게시글 좋아요 취소";
        }
    }
    @Transactional
    public String likeComment(Long postId, Long commentId, User user) {
        Post post=postRepository.findById(postId).orElseThrow(()-> new IllegalArgumentException("게시글이 존재하지 않습니다."));
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new IllegalArgumentException("댓글이 존재하지 않습니다"));
        Like like= new Like(user,post,comment);

        Like likeExist = likeRepository.findByUserIdAndPostIdAndCommentId(user.getId(),postId,commentId);

        if(likeExist==null){
            likeRepository.save(like);
            comment.like();
            return "댓글 좋아요";
        }else {
            likeRepository.delete(likeExist);
            comment.disLike();
            return "댓글 좋아요 취소";
        }
    }
}
