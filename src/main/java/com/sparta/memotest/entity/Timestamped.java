package com.sparta.memotest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
//AuditingEntityListener 이 기능을 사용하려면 최상단 클래스에(가장 처음 Controller) @EnableJpaAuditing 달아줘야 함
@EntityListeners(AuditingEntityListener.class)//이걸 달아줘야 자동으로 시간을 넣어준다
//abstract 로 만드는 이유 : Timestamped를 단독으로 사용할 이유가 없어서
public abstract class Timestamped {

    @CreatedDate
    @Column(updatable = false)//최초 시간을 넣어주기위해 false로 한다 // 이게 true 면 최신 시간으로 변경된다
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @LastModifiedDate//변경할때 변경된 시간이 자동으로 저장된다
    @Column
    @Temporal(TemporalType.TIMESTAMP)//날짜데이터를 맵핑할때 사용
    private LocalDateTime modifiedAt;
}