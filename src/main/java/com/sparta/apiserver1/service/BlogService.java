package com.sparta.apiserver1.service;

import com.sparta.apiserver1.dto.BlogRequestDto;
import com.sparta.apiserver1.dto.BlogResponseDto;
import com.sparta.apiserver1.entity.Blog;
import com.sparta.apiserver1.entity.User;
import com.sparta.apiserver1.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<BlogResponseDto> getBlogALL() {
        return blogRepository.findAllByOrderByCreatedAtDesc().stream().map(BlogResponseDto::new).toList();
    }

    public BlogResponseDto getBlogByID(Long id) {
        Blog Blog = blogRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        return new BlogResponseDto(Blog);
    }

    private Blog findBlog(Long id) {
        return blogRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 블로는 존재하지 않습니다"));
    };

    @Transactional
    public BlogResponseDto addPost(BlogRequestDto requestDto, User user) {
        Blog blog = new Blog(requestDto,user);
        Blog saveBlog = blogRepository.save(blog);
        BlogResponseDto blogResponseDto = new BlogResponseDto(saveBlog);
        return  blogResponseDto;
    }

}
