package com.sparta.memotest.dto;

import lombok.Getter;

@Getter
public class MemoTestRequestDto {
    private String username;
    private String userPassword;
    private String title;
    private String contents;
}