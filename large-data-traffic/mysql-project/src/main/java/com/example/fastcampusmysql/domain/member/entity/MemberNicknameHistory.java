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

/**
 * - 과거의 정보를 가지고 있어야 하는 history 성 데이터들은 정규화의 대상이 아님.
 *     - 실무에서 이런 상황이 굉장히 헷갈림.
 *     - 정규화를 하려고 한다면, 정규화하려고 하는 대상이 항상 데이터의 최신성을 보장해야 하는 데이터인가? 를 잘 고려해야 함.
 * - ex) 실무에서의 모호한 케이스
 *     - 이커머스에서, 주문 내역에 제조사의 이름을 남김. → 당연히 제조사의 식별자를 남긴다.
 *     - 만약 제조사의 이름이 바뀐다면 주문내역에는 제조사의 이름이 바뀐내역으로 남아야할지, 이전 내역으로 남아야할지는 **어떤 비즈니스 정책을 가져가는지에 따라 달라짐.**
 *     - 어떤 방향으로 갈지 모호한 상태이고, 이 데이터가 항상 최신성을 유지해야되는가? 를 고민해야 함.
 *     - 이게 애매하다면, PO, PM, 기획자분들에게 가서 요구사항을 파내는 노력을 해야함.
 */
