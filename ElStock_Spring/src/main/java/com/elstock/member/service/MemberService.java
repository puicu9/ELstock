package com.elstock.member.service;

import com.elstock.member.entity.Member;
import com.elstock.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service // Controller가 요청한 내용에 대한 업무 로직을 처리해주는 클래스
@RequiredArgsConstructor // final 또는 @NotNull이 붙어 있는 필드에서 적합한 생성자를 만들어서 데이터를 주입해줍니다
@Transactional // 비즈니스 로직을 처리해주는 서비스 계층에서 오류가 발생 시 로직 수행 이전 상태로 되돌려 줍니다.
public class MemberService implements UserDetailsService { // MemberService02
    private final MemberRepository memberRepository ; // MemberService01

    // 회원 중복 체크 이후에 회원 정보를 저장합니다.
    public Member saveMember(Member member){ // MemberService01
        this.validateDuplicateMember(member) ;
        return this.memberRepository.save(member) ;
    }

    private void validateDuplicateMember(Member member){ // MemberService01
        // 동일한 이메일이 존재하는 지 검사하고, 존재하면 예외를 발생시킵니다.
        Member findMember = memberRepository.findByEmail(member.getEmail()) ;
        if(findMember != null) { // 가입하면 안됨
            String errorMessage = "이미 가입된 회원입니다." ;
            throw new IllegalStateException(errorMessage) ;
        }
    }

    // loadUserByUsername() 메소드의 매개 변수에는 실질적 기본키에 해당하는 email을 넣어 주도록 합니다.
    @Override // MemberService02
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = this.memberRepository.findByEmail(email) ;

        if(member == null){
            throw new UsernameNotFoundException(email) ;
        }
    
        // UserDetails 인터페이스의 구현체 클래스 User 객체를 생성하여 반환해 줍니다.
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build() ;

    }
}
