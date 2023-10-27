package com.hcs.couponevent.repository;

import com.hcs.member.Member;
import com.hcs.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Commit
    void save(){
        Member newMember = Member.newMember();
        Member savedMember = memberRepository.save(newMember);

        Assertions.assertThat(newMember).isEqualTo(savedMember);

    }

}