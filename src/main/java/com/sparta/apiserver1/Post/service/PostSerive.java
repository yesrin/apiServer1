package com.sparta.apiserver1.Post.service;

import com.sparta.apiserver1.Common.security.UserDetailsImpl;
import com.sparta.apiserver1.Post.dto.PostRequestDto;
import com.sparta.apiserver1.Post.dto.PostResponseDto;
import com.sparta.apiserver1.Post.entity.Post;
import com.sparta.apiserver1.Post.repository.PostRepository;
import com.sparta.apiserver1.User.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class PostSerive {

    private final PostRepository postRepository;
    private final MessageSource messageSource;

    public List<PostResponseDto> getAllPostAndComment() {
       return postRepository.findAllByOrderByCreatedAtDesc().stream().map(PostResponseDto::new).toList();
    }

    public PostResponseDto getPostByID(Long id) {
        Post Post = postRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException(
                        messageSource.getMessage(
                "post.not.exist",
                null ,
                "post does not exist",
                Locale.getDefault()
        )));

        return new PostResponseDto(Post);
    }

    public Long deletePost(Long id, User user) {
        Post post = findPost(id);
        //요청자(user) 가 같은지 체크
        if (!post.getUsername().equals(user.getUsername())) {
            throw new IllegalArgumentException(
                    messageSource.getMessage(
                            "only.writer.delete",
                            null ,
                            "only writer can delete",
                            Locale.getDefault()
                    )
            );
        }
        postRepository.delete(post);
        return id;
    }

    private Post findPost(Long id) {
        return postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException( messageSource.getMessage(
                        "post.not.exist",
                        null ,
                        "post does not exist",
                        Locale.getDefault()
                )));
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

        //요청자(user) 가 같은지 체크
        if (!post.getUsername().equals(user.getUsername())) {
            throw new IllegalArgumentException(
                    messageSource.getMessage(
                            "only.writer.update",
                            null ,
                            "only writer can update",
                            Locale.getDefault()
                    )
            );
        }

        post.update(requestDto);

        return new PostResponseDto(post);
    }

}
