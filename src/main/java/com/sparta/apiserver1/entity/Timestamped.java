package com.sparta.apiserver1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Timestamped {

    @CreatedDate //객체가 생성되어 저장될때 시간값이 자동으로 저장
    @Column(updatable = false) // 최초 생성시간만 저장 , 그 이후 수정x
    @Temporal(TemporalType.TIMESTAMP) //자바의 날짜 데이터를 매핑할때 사용함.
    private LocalDateTime createdAt;

    @LastModifiedDate //변경이 될때마다 그 시간으로 저장됨
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifiedAt;
}
