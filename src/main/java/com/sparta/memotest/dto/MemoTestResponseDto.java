package com.sparta.memotest.dto;

import com.sparta.memotest.entity.MemoTest;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemoTestResponseDto {
    private Long id;
    private String username;
    private String title;
    private String contents;
    private LocalDateTime createdAt;

    public MemoTestResponseDto(MemoTest memo) {
        this.id = memo.getId();
        this.username = memo.getUsername();
        this.title = memo.getTitle();
        this.contents = memo.getContents();
        this.createdAt = memo.getCreatedAt();
    }


}