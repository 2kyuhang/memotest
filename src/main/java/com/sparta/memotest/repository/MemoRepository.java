package com.sparta.memotest.repository;

import com.sparta.memotest.entity.MemoTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//@Component /*MemoRepository클래스를 bean으로 만들어 준다*/
//@Repository 가 Component 를 사용하고 있어 생략가능
//@Repository

//extends JpaRepository 를 하면 @Repository 생략 가능
//이유는 : @JpaRepository에서 이미 @Repository를 받기 때문
public interface MemoRepository extends JpaRepository<MemoTest, Long> {

    List<MemoTest> findAllByOrderByModifiedAtDesc();

    List<MemoTest> findAllByOrderByCreatedAtDesc();

    List<MemoTest> findAllByUsername(String name);

    List<MemoTest> findAllByContentsContainsOrderByModifiedAtDesc(String keyword);

}

