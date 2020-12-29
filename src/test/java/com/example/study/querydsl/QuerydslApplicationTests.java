package com.example.study.querydsl;

import com.example.study.querydsl.entity.Member;
import com.example.study.querydsl.entity.QMember;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
@Commit
class QuerydslApplicationTests {

    @Autowired
    EntityManager em;

    @Test
    void contextLoads() {
        Member member = new Member();
        em.persist(member);

        JPAQueryFactory query = new JPAQueryFactory(em);
        QMember findMember = QMember.member;

        Member result = query
                .selectFrom(findMember)
                .fetchOne();

        assertThat(result).isEqualTo(member);
        assertThat(result.getId()).isEqualTo(member.getId());
    }

}
