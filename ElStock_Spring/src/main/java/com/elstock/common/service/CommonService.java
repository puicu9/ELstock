package com.elstock.common.service;

import com.elstock.common.repository.ConmmonRepository;
import com.elstock.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommonService {

    private final ConmmonRepository conmmonRepository;

    @Transactional(readOnly = true)
    public Member getMemberInfo(String email) {

        Member member = this.conmmonRepository.findByEmail(email);

        return member ;

    }

}
