package com.example.fastcampusmysql.domain.member.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class MemberNicknameHistory {
    private final Long id;
    private final Long memberId;
    // 과거의 정보를 가지고 있어야 하는 history 성 데이터들은
    // 정규화의 대상이 아님.
    // 정규화를 하려고 한다면, 정규화하려고 하는 대상이 항상 데이터의 최신성을 보장해야 하는 데이터인가? 를 잘 고려해야 함.
    private final String nickname;
    private final LocalDateTime createdAt;

    @Builder
    public MemberNicknameHistory(Long id, Long memberId, String nickname, LocalDateTime createdAt) {
        this.id = id;
        this.memberId = Objects.requireNonNull(memberId);
        this.nickname = Objects.requireNonNull(nickname);
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }
}
