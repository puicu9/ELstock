package com.elstock.common.repository;

import com.elstock.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConmmonRepository extends JpaRepository<Member, Long> {


    Member findByEmail(String email);

}
