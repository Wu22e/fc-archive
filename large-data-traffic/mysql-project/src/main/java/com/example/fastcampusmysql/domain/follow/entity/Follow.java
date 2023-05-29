package com.example.fastcampusmysql.domain.follow.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Follow {
    private final Long id;

    private final Long fromMemberId;

    private final Long toMemberId;

    private final LocalDateTime createdAt;

    @Builder
    public Follow(Long id, Long fromMemberId, Long toMemberId, LocalDateTime createdAt) {
        this.id = id;
        this.fromMemberId = Objects.requireNonNull(fromMemberId);
        this.toMemberId = Objects.requireNonNull(toMemberId);
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }
}

/**
 *
 * follow 엔티티 → 두 명의 엔티티를 연결하는 역할을 함.
 *
 * - 요청/승인 없이 바로 팔로우가 되도록 구현
 * - 나중에 다시 프로젝트 리팩토링, 추가할 때 이 과정을 넣어서 개발하면 재밌는 포인트가 될듯
 *
 * Follow 엔티티 설계 고민
 *
 * - 팔로우하면, 팔로우 한 사람과 당한 사람 2개에 대한 정보를 갖는다.
 * - 보통 팔로우한 사람의 이름을 가져오지 identity 를 가져오진 않는다.
 * - 팔로우한 사람, 팔로우 당한 사람의 닉네임을 Follow 테이블에 넣어야 할까? 아니면 식별자만 가지고 있다가 join 해서 데이터를 가져올건지 판단해야 함.
 *
 * - Follow 성격을 잘 보면 우선 항상 이름이라는 데이터를 따라다니는 것은 맞는것 같음.
 *     - 식별자로 노출되는 경우는 거의 없었음.. (뒷단에서는 식별자로 처리를 한다고 해도..)
 * - 데이터의 최신성을 보장해야 하나?
 *     - 이번 케이스는 필요해보임.
 *     - 3달전의 닉네임을 보여주면 이상하다.. 항상 데이터의 최신성을 보장해야한다.
 *     - 정규화를 적용한다.
 *     - 중복을 제거하는게 훨씬 유리하다고 판단함.
 *         - 데이터의 최신성을 보장해야하기 때문
 *         - 조회의 편리를 위해, 필드를 추가해야 한다면..
 *             - 닉네임의 변경이 잦은데, 백만명의 팔로우를 가지고 있는 인플루언서가 닉네임을 변경했다면, 백만개의 레코드를 다 업데이트 쳐야함..
 *             - 관리가 쉽지 않아보임.. 정규화가 필요함.
 * - 연관된 닉네임 데이터는 어떻게 가져올 것인가?
 *     - 제일 쉬운 방법 : 조인
 *     - 조인하지 않고, 쿼리를 한번 더 날린다.
 *     - 별도 저장소(데이터)를 이용한다.
 *         - 조인은 가능하면 미루는게 좋음.
 *         - 조인을 함으로써 두 도메인이 엄청난 강 결합이 이루어지게 됨.
 *             - ex) follow - member ← member가 follow layer로 침투하게 됨.
 *         - 프로젝트 초기부터 강결합을 하는것 추후에 유연성있는 아키텍처나 시스템이 되기 힘듦.
 *             - 처음에는 결합을 낮추는데 집중하는게 좋다.
 *         - 또 조인의 안좋은 점은 결합이 강하게 되기 때문에 추후에 아키텍처적으로 성능 문제를 풀기가 힘들어 지고 리팩토링도 힘들어짐.
 *             - 근데 또, 성능 문제가 조인을 한다고 해서 해결되는 것도 아님.
 *             - 쿼리는 한번 더 줄겠지만, 피쳐가 하나하나 쌓이다 보면 조인 쿼리도 무거워지고, 조인 쿼리는 또, 일반 select 쿼리보다 훨씬 무거운 성능을 냄..
 *             - 그리고 캐시같은 여러 성능이 좋은 조회용 데이터베이스를 활용할 기회를 잃어버리게 됨..
 * - 이번 클립에서는 조인을 하지않고 쿼리를 한번 더 날리는 방식으로 구현함.
 */
