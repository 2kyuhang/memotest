package com.sparta.memotest.service;

import com.sparta.memotest.dto.MemoTestDeleteRequestDto;
import com.sparta.memotest.dto.MemoTestResponseDto;
import com.sparta.memotest.dto.MemoTestRequestDto;
import com.sparta.memotest.dto.MemoTestUpdateRequestDto;
import com.sparta.memotest.entity.MemoTest;
import com.sparta.memotest.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Component
//@Service 가 Component 를 사용하고 있어 생략가능
@Service
//@RequiredArgsConstructor 을 통해 final 을 생성자로 가져가게 하면 자동으로 @Autowired 가 붙는거임
public class MemoService {

    private final MemoRepository memoRepository;

    @Autowired
    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    //1. 모든 메모 작성기준으로 Desc 가져오기(제목, 작성자명, 작성 내용, 작성 날짜)
    public List<MemoTestResponseDto> getMemos() {
        return memoRepository.findAllByOrderByCreatedAtDesc().stream().map(MemoTestResponseDto::new).toList();
    }

    //2. 게시글 작성(제목, 작성자명, 비밀번호, 작성 내용)
    public MemoTestResponseDto createMemo(MemoTestRequestDto requestDto) {
        MemoTest memo = new MemoTest(requestDto);

        MemoTest saveMemo = memoRepository.save(memo);
        //필요한 정보만 반환하기 위해 memoResponseDto에 값을 넣고 반환
        MemoTestResponseDto memoResponseDto = new MemoTestResponseDto(saveMemo);

        return memoResponseDto;
    }

    //3. 게시글 조회
    public MemoTestResponseDto searchMemo(Long id){
        MemoTest memo = findMemo(id);
        return new MemoTestResponseDto(memo);
    }

    //4. 게시글 수정
    @Transactional
    public MemoTestResponseDto updateMemo(MemoTestUpdateRequestDto requestDto) {
        MemoTest memo = findMemo(requestDto.getId());

        if(memo.getUserpassword().equals(requestDto.getUserpassword())){
            //제목, 작성자명, 작성 내용을 memo객체에 넣음으로 변경 감지
            memo.update(requestDto);
            MemoTest saveMemo = memoRepository.save(memo);
            MemoTestResponseDto memoResponseDto = new MemoTestResponseDto(saveMemo);
            return memoResponseDto;
        }else {
            new IllegalArgumentException("비밀번호를 다시 입력!");
            return null;
        }
    }

    //5. 게시글 삭제
    @Transactional
    public String deleteMemo(MemoTestDeleteRequestDto requestDto) {
        MemoTest memo = findMemo(requestDto.getId());

        System.out.println("비밀번호 : "+memo);
        System.out.println("비밀번호 확인 : "+requestDto.getUserpassword());

        if(memo.getUserpassword().equals(requestDto.getUserpassword())){
            memoRepository.delete(memo);
            return "삭제 성공";
        }else {
            new IllegalArgumentException("비밀번호를 다시 입력!");
            return "삭제 실패";
        }
    }


    private MemoTest findMemo(Long id) {
        return memoRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 메모는 존재하지 않습니다."));
    }

}
