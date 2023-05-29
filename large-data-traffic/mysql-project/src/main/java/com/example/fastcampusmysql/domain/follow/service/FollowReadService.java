package com.example.fastcampusmysql.domain.follow.service;

import com.example.fastcampusmysql.domain.follow.entity.Follow;
import com.example.fastcampusmysql.domain.follow.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FollowReadService {
    private final FollowRepository followRepository;

    // Follow API 의 경우, 외부 API로 나가는 경우가 없을 것 같음.
    // 왜냐면, 컨트롤러에서 회원 정보를 반환할 것이기 때문 (MemberDto)
    // 그래서 아직까지는 Domain Entity를 직접 반환해도 외부 도메인, 외부 서비스에 흘러가지 않아서
    public List<Follow> getFollowings(Long memberId) {
        return followRepository.findAllByFromMemberId(memberId);
    }
}
