package com.sparta.apiserver1.service;

import com.sparta.apiserver1.dto.PostRequestDto;
import com.sparta.apiserver1.dto.PostResponseDto;
import com.sparta.apiserver1.entity.Post;
import com.sparta.apiserver1.entity.User;
import com.sparta.apiserver1.repository.PostRepository;
import com.sparta.apiserver1.security.UserDetailsImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostSerive {

    private final PostRepository postRepository;

    public PostSerive(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

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

//        // post의 userid와 user의 id가 일치하지 않으면 오류메세지
//        if(!user.equals(post.getUser())) {
//            new IllegalArgumentException("작성자가 일치하지 않습니다");
//        }

//        // usrname으로
//        if(!user.getUsername().equals(post.getUsername())) {
//            new IllegalArgumentException("작성자가 일치하지 않습니다");
//         }

        post.checkUsername(user.getUsername());
        post.update(requestDto);

        return new PostResponseDto(post);
    }

}
