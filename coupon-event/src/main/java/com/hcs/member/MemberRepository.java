package com.hcs.member;

import com.hcs.member.Member;
import com.hcs.member.MemberId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, MemberId> {
}
