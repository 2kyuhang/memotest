package com.sparta.memotest.dto;

import lombok.Getter;

@Getter
public class MemoTestUpdateRequestDto {
    private Long id;
    private String username;
    private String userpassword;
    private String title;
    private String contents;
}
