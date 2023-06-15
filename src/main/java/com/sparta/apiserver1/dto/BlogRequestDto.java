package com.sparta.apiserver1.dto;

import lombok.Getter;

@Getter
public class BlogRequestDto {
    private Long id;
    private String title;
    private String username;
    private String contents;
}
