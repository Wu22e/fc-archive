package com.example.fastcampusmysql.util;

import com.example.fastcampusmysql.domain.member.entity.Member;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

public class MemberFixtureFactory {
    // 테스트 코드 작성할 때, 몇가지 허들이 있음.
    // 그 중에 하나는, 테스트 객체를 어떻게 생설할 것인가에 대한 문제임.
    // 아래와 같이 단순히 빌더를 이용해 값을 채워넣어도 되지만, 이 값들은 테스트 케이스마다 너무 다양해질 수 있음.
    // 그래서 이런 것들을 도와주기 위해 fixture library 를 많이 사용함. ex) EasyRandom (Github 참고)
    public static Member create(Long seed) {
        // seed 값을 설정해주지 않으면 동일한 seed 값으로 같은 객체를 계속반환하게 됨.
        var param = new EasyRandomParameters().seed(seed);
        return new EasyRandom(param).nextObject(Member.class);
    }

    public static Member create() {
        // default seed 는 123L임.
        var param = new EasyRandomParameters();
        return new EasyRandom(param).nextObject(Member.class);
    }
}
