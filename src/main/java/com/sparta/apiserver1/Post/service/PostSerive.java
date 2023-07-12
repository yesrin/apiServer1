package com.sparta.apiserver1.Post.service;

import com.sparta.apiserver1.Post.dto.PostRequestDto;
import com.sparta.apiserver1.Post.dto.PostResponseDto;
import com.sparta.apiserver1.Post.entity.Post;
import com.sparta.apiserver1.User.entity.User;
import com.sparta.apiserver1.Post.repository.PostRepository;
import com.sparta.apiserver1.Common.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostSerive {

    private final PostRepository postRepository;

    public List<PostResponseDto> getPostALL() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    public PostResponseDto getPostByID(Long id) {
        Post Post = postRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        return new PostResponseDto(Post);
    }

    public Long deletePost(Long id, UserDetailsImpl userDetails) {
        Post post = findPost(id);
        post.checkUsername(userDetails.getUsername());
        postRepository.delete(post);
        return id;
    }

    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시물이 존재하지 않습니다"));
    };
    @Transactional
    public PostResponseDto addPost(PostRequestDto requestDto, User user) {
        Post post = new Post(requestDto,user);
        Post savePost = postRepository.save(post);
        PostResponseDto postResponseDto= new PostResponseDto(savePost);
        return postResponseDto;
    }

    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto, User user) {

        Post post = findPost(id);

        post.checkUsername(user.getUsername());
        post.update(requestDto);

        return new PostResponseDto(post);
    }

}
