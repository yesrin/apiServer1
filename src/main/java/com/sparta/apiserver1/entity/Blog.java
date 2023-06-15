package com.sparta.apiserver1.entity;

import com.sparta.apiserver1.dto.BlogRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "blog")
@NoArgsConstructor
public class Blog extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name="password", nullable = false)
    private String password;
    @Column(name = "contents", nullable = false, length = 500)
    private String contents;

    public Blog(BlogRequestDto requestDto) {
        this.title=requestDto.getTitle();
        this.username=requestDto.getUsername();
        this.password=requestDto.getPassword();
        this.contents= requestDto.getContents();
    }

    public void update(BlogRequestDto requestDto) {
        this.title=requestDto.getTitle();
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }
}
