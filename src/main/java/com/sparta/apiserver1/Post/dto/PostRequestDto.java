package com.sparta.apiserver1.Post.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class PostRequestDto {
    private String title;
    private String contents;

}