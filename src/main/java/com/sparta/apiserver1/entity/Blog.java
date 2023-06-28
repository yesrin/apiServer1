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

    @Column(name = "contents", nullable = false, length = 500)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name="username", nullable = false)
    private String  username;


    public Blog(BlogRequestDto requestDto, User user) {
        this.user=user;
        this.title=requestDto.getTitle();
        this.contents= requestDto.getContents();
        this.username=user.getUsername();
    }

    public void update(BlogRequestDto requestDto) {
        this.title=requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
}
