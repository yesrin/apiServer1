package com.sparta.apiserver1.Post.service;

import com.sparta.apiserver1.Common.exception.NotFoundException;
import com.sparta.apiserver1.Post.dto.PostRequestDto;
import com.sparta.apiserver1.Post.dto.PostResponseDto;
import com.sparta.apiserver1.Post.entity.Post;
import com.sparta.apiserver1.Post.repository.PostRepository;
import com.sparta.apiserver1.User.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostSerive {

    private final PostRepository postRepository;
    private final MessageSource messageSource;

    @Override
    public List<PostResponseDto> getAllPostAndComment() {
        return postRepository.findAllByOrderByCreatedAtDesc().stream().map(PostResponseDto::new).toList();
    }
    @Override
    public List<PostResponseDto> searchPost(String keyword, Pageable pageable) {

        return postRepository.search(keyword,pageable).stream().map(PostResponseDto::new).toList();
    }

    @Override
    public Long deletePost(Long id, User user) {
        Post post = findPost(id);
        //요청자(user) 가 같은지 체크
        if (!post.getUsername().equals(user.getUsername())) {
            throw new IllegalArgumentException(
                    messageSource.getMessage(
                            "only.writer.delete",
                            null,
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
                new NotFoundException(messageSource.getMessage(
                        "post.not.exist",
                        null,
                        "post does not exist",
                        Locale.getDefault()
                )));
    }

    ;

    @Override
    @Transactional
    public PostResponseDto addPost(PostRequestDto requestDto, User user) {
        Post post = new Post(requestDto, user);
        Post savePost = postRepository.save(post);
        PostResponseDto postResponseDto = new PostResponseDto(savePost);
        return postResponseDto;
    }

    @Override
    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto requestDto, User user) {

        Post post = findPost(id);

        //작성자가 아니거나 이거나 관리자이가 아니면 예외처리
        if(!user.getRole().getAuthority().equals("ROLE_ADMIN")){

            if (!post.getUsername().equals(user.getUsername())) {
                throw new IllegalArgumentException(
                        messageSource.getMessage(
                                "not.have.role",
                                null,
                                "Not Have Role",
                                Locale.getDefault()
                        ));
            }
        }

        post.update(requestDto);

        return new PostResponseDto(post);
    }

}
