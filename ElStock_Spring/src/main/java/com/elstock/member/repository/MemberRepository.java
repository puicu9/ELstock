package com.elstock.member.repository;

import com.elstock.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

// MemberRepository01
public interface MemberRepository extends JpaRepository<Member, Long> {
    // 이메일을 이용한 회원 검색
    // 회원 가입 시 중복 체크를 위한 용도로 사용될 예정입니다.
    Member findByEmail(String email) ;
}
