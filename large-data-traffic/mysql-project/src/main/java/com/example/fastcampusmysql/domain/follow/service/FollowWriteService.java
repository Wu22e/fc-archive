package com.example.fastcampusmysql.domain.follow.service;

import com.example.fastcampusmysql.domain.follow.entity.Follow;
import com.example.fastcampusmysql.domain.follow.repository.FollowRepository;
import com.example.fastcampusmysql.domain.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@RequiredArgsConstructor
@Service
public class FollowWriteService {
    private final FollowRepository followRepository;

    // create 메서드로 memberId 를 받게 되면 memberId 가 올바른지 검증해야 되는데,
    // 그렇게 되면 MemberService 나 MemberRepository 를 주입받아야 하는데.. 그럼 결합이 너무 심해짐.. 구체적인 구현도 너무 많이 알게됨..
    // 그래서, id를 받지 않고, 도메인 계층간의 이동인 MemberDto를 받는다.
    // 그럼 이 MemberDto는 누가 넘겨줄 것인가? 실무에서 이런 고민을 계속 하게 됨.
    // 물론 프론트에서 넘겨 줄 수도 있겠지만..
    // 서로 다른 도메인의 데이터를 주고 받아야 할 때, 서로 다른 도메인의 흐름을 제어해야할 때, 어디서 해야 하는가? 고민을 하게 됨.
    // 여러가지 방법론 들이 있음. 헥사고날 아키텍처, DDD, 레이어드 아키텍처, 등등.
    // 이번 클립에서는 대용량 시스템 이해를 위한 mysql 인 만큼, 심플하게 가져간다.
    // 어플리케이션 레이어를 하나 두고, 유즈케이스 레이어에서 여러 도메인의 레이어를 오케스트레이션, 즉, 흐름을 제어하는 역할을 한다.
    public void create(MemberDto fromMember, MemberDto toMember) {
        /*
            from, to 회원 정보를 받아서
            저장할텐데...
            fro <-> to validate
         */
        Assert.isTrue(!fromMember.id().equals(toMember.id()), "From, To 회원이 동일합니다.");

        var follow = Follow.builder()
                .fromMemberId(fromMember.id())
                .toMemberId(toMember.id())
                .build();
        followRepository.save(follow);
    }
}
