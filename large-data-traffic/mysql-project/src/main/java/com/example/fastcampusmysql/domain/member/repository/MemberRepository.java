package com.example.fastcampusmysql.domain.member.repository;


import com.example.fastcampusmysql.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Member save(Member member) {
        /*
            member id를 보고 갱신 또는 삽입을 정함
            반환값은 id를 담아서 반환한다.
         */
        if (member.getId() == null) {
            return insert(member);
        }
        return update(member);
    }

    private Member insert(Member member) {
        // SimpleJdbcInsert 를 쓰면 insert 쿼리 이후, id 를 받아오는 걸 굉장히 쉽게 구현할 수 있다.
        // NameParameterJdbcTemplate 에, KeyHolder 를 이용하면 key 를 받아올 수 있음.
        // 하지만 코드가 지저분해지고 실수의 여지가 많아져서 SimpleJdbcInsert 를 사용해본다.
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName("Member")
                .usingGeneratedKeyColumns("id"); // key 를 어떤 칼럼으로 가져올 지 명시해줄 수 있음.

        // bean 으로 SqlParameterSource 를 만들어 준다.
        // pojo 객체인 member 를 넣어주면 SqlParameterSource 인터페이스에 맞는 파라미터를 추출해서 만들어줌.
        SqlParameterSource params = new BeanPropertySqlParameterSource(member);
        var id = simpleJdbcInsert.executeAndReturnKey(params).longValue();
        return Member
                .builder()
                .id(id)
                .email(member.getEmail())
                .nickname(member.getNickname())
                .birthday(member.getBirthday())
                .createdAt(member.getCreatedAt())
                .build();
    }

    private Member update(Member member) {
        // TODO: implemented
        return member;
    }

}
