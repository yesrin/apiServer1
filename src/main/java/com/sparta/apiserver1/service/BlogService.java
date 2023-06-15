package com.sparta.apiserver1.service;

import com.sparta.apiserver1.dto.BlogRequestDto;
import com.sparta.apiserver1.dto.BlogResponseDto;
import com.sparta.apiserver1.entity.Blog;
import com.sparta.apiserver1.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public BlogResponseDto createBlog(BlogRequestDto requestDto) {
        Blog blog = new Blog(requestDto);
        Blog saveBlog = blogRepository.save(blog);
        BlogResponseDto blogResponseDto = new BlogResponseDto(saveBlog);
        return  blogResponseDto;
    }

    public List<BlogResponseDto> getBlogALL() {
        return blogRepository.findAllByOrderByCreatedAtDesc().stream().map(BlogResponseDto::new).toList();
    }

    public BlogResponseDto getBlogByID(Long id) {
        Blog Blog = blogRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("해당 게시물이 존재하지 않습니다."));

        return new BlogResponseDto(Blog);
    }

    public Long updateBlog(Long id, BlogRequestDto requestDto) {
        Blog blog = findBlog(id);
        blog.update(requestDto);
        return id;
    }

    public Long deleteBlog(Long id) {
        Blog blog = findBlog(id);
        blogRepository.delete(blog);
        return id;
    }

    private Blog findBlog(Long id) {
        return blogRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 블로는 존재하지 않습니다"));
    };

}
