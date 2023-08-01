package com.sparta.apiserver1.Post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
public class PostRequestDto {
    private final String title;
    private final String contents;

}