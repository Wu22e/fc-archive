package com.example.fastcampusmysql.domain.member.entity;

import com.example.fastcampusmysql.util.MemberFixtureFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.*;

class MemberTest {

    @DisplayName("회원은 닉네임을 변경할 수 있다.")
    @Test
    void testChangeName() {
        // 테스트에 멤버가 필요한 경우, 그 테스트 코드마다 Member를 만드는 비슷한 코드가 중복된다.
        // (이런식으로 테스트에 필요한 객체를 만드는 것을 ObjectMother 패턴이라고 함. (ex: EasyRandom) -> martinFowler.com 에 검색)
        var member = MemberFixtureFactory.create();
        var expected = "khw";

        member.changeNickname(expected);

        Assertions.assertEquals(expected, member.getNickname());
    }

    @DisplayName("회원의 닉네임은 10자를 초과할 수 없다.")
    @Test
    void testNicknameMaxLength() {
        var member = MemberFixtureFactory.create();
        var overMaxLengthName = "khwkhwkhwkhw";

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> member.changeNickname(overMaxLengthName)
        );
    }
}