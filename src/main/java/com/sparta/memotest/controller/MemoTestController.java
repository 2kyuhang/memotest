package com.sparta.memotest.controller;

import com.sparta.memotest.dto.MemoTestDeleteRequestDto;
import com.sparta.memotest.dto.MemoTestResponseDto;
import com.sparta.memotest.dto.MemoTestRequestDto;
import com.sparta.memotest.dto.MemoTestUpdateRequestDto;
import com.sparta.memotest.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MemoTestController {

    private MemoService memoService;
    @Autowired
    public MemoTestController(MemoService memoService) {
        this.memoService = memoService;
    }


    @PostMapping("/memos")
    public MemoTestResponseDto createMemo(@RequestBody MemoTestRequestDto requestDto) {
        return memoService.createMemo(requestDto);
    }

    @GetMapping("/memos")
    public List<MemoTestResponseDto> getMemos() {
        return memoService.getMemos();
    }

    @GetMapping("/memos/{id}")
    public MemoTestResponseDto searchMemo(@PathVariable Long id) {
        return memoService.searchMemo(id);
    }

    @PutMapping("/memos")
    public MemoTestResponseDto updateMemo(@RequestBody MemoTestUpdateRequestDto requestDto) {
        return memoService.updateMemo(requestDto);
    }

    @DeleteMapping("/memos")
    public String deleteMemo(@RequestBody MemoTestDeleteRequestDto requestDto) {//@RequestBody 로 deleteMemo 객체 만들어서 사용하자
        return memoService.deleteMemo(requestDto);
    }

}