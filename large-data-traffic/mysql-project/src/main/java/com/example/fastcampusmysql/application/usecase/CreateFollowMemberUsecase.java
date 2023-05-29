package com.example.fastcampusmysql.application.usecase;

import com.example.fastcampusmysql.domain.follow.service.FollowWriteService;
import com.example.fastcampusmysql.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// 클래스 명을 동사로 쓴 이유는 기능 하나만 하는 유즈케이스를 만들기 위해서 명시적으로 썼음.
@RequiredArgsConstructor
@Service
public class CreateFollowMemberUsecase {
    // read / write 분리해서 얻은 이점이 여기서 나옴.
    // 이 use case 는 회원에 대한 쓰기 권한이 전혀 없다는 것을 알 수 있음.
    private final MemberReadService memberReadService;
    private final FollowWriteService followWriteService;

    // 유즈케이스의 메서드 명은 execute 라는 이름으로 동일하게 가져감.
    public void execute(Long fromMemberId, Long toMemberId) {
        /*
            1. 입력받은 memberId로 회원조회
            2. FollowWriteService.create()
         */

        // 유즈케이스 레이어에서는 가능한 로직이 없어야 함.
        // 유즈케이스는 도메인 서비스의 흐름을 제어하는 역할만 해야 함.
        // 각 도메인에 대한 비즈니스 로직은 각 도메인 서비스에 들어 있어야 함.

        var fromMember = memberReadService.getMember(fromMemberId);
        var toMember = memberReadService.getMember(toMemberId);

        followWriteService.create(fromMember, toMember);
    }
}
