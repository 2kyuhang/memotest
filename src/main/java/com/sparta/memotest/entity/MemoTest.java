package com.sparta.memotest.entity;

import com.sparta.memotest.dto.MemoTestRequestDto;
import com.sparta.memotest.dto.MemoTestUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "memotest")
@NoArgsConstructor
public class MemoTest extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "userpassword", nullable = false)
    private String userpassword;

    @Column(name = "title", nullable = false, length = 500)
    private String title;
    @Column(name = "contents", nullable = false, length = 500)
    private String contents;


    public MemoTest(MemoTestRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.userpassword = requestDto.getUserPassword();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void update(MemoTestUpdateRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
}