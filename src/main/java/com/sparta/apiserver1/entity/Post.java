package com.sparta.apiserver1.entity;

import com.sparta.apiserver1.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "post")
@NoArgsConstructor
public class Post extends Timestamped{
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


    public Post(PostRequestDto requestDto, User user) {
        this.user=user;
        this.title=requestDto.getTitle();
        this.contents= requestDto.getContents();
        this.username= user.getUsername();
    }

    public void update(PostRequestDto requestDto) {
        this.title=requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void checkUsername(String username) {
        if(!this.username.equals(username)) {
            throw new IllegalArgumentException("작성자가 일치하지 않습니다.");
        }
    }
}
