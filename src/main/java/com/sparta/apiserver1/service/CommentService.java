package com.sparta.apiserver1.service;


import com.sparta.apiserver1.dto.CommentRequestDto;
import com.sparta.apiserver1.dto.CommentResponseDto;
import com.sparta.apiserver1.entity.Comment;
import com.sparta.apiserver1.entity.Post;
import com.sparta.apiserver1.entity.User;
import com.sparta.apiserver1.repository.CommentRepository;
import com.sparta.apiserver1.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Transactional
    public CommentResponseDto addComment(Long postId,CommentRequestDto requestDto, User user) {
        Post post =findPost(postId);
        Comment comment= new Comment(requestDto,user,post);
        Comment saveComment= commentRepository.save(comment);

        return new CommentResponseDto(saveComment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long postId,Long commentId ,CommentRequestDto requestDto, User user) {
        findPost(postId);
        Comment comment =commentRepository.findById(commentId).orElseThrow(()->new IllegalArgumentException("선택한 댓글이 존재하지 않습니다."));
        comment.checkUsername(user.getUsername()); // 지금 로그인한 사람이 댓글 작성한 사람이 맞는지 확인
        comment.update(requestDto);
        return new CommentResponseDto(comment);

    }

    private Post findPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시물이 존재하지 않습니다"));
    };

}



