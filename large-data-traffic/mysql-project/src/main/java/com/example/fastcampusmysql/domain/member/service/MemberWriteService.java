package com.example.fastcampusmysql.domain.member.service;

import com.example.fastcampusmysql.domain.member.dto.RegisterMemberCommand;
import com.example.fastcampusmysql.domain.member.entity.Member;
import com.example.fastcampusmysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// 대용량 시스템에서 조회와 쓰기 관점을 분리하는게 굉장히 중요함.
// 그 연습을 하기 위해 조금 무리하더라도 read / write service 를 분리해서 진행한다.
// 초기에는 간단한 CRUD 에서 시작하지만, 비즈니스가 복잡해질 수록
// read service 에 대한 의존성과, write service 에서 필요한 의존성들이 엄청 달라지게 됨.

@RequiredArgsConstructor
@Service
public class MemberWriteService {
    private final MemberRepository memberRepository;

    public Member register(RegisterMemberCommand command) {
        /*
            목표 - 회원정보(이메일, 닉네임, 생년월일)를 등록한다.
                - 닉네임은 10자를 넘길 수 없다.
            파라미터 - memberRegisterCommand

            val member = Member.of(memberRegisterCommand)
            memberRepository.save(member)
         */
        var member = Member.builder()
                .nickname(command.nickname())
                .email(command.email())
                .birthday(command.birthday())
                .build();
        return memberRepository.save(member);
    }
}
