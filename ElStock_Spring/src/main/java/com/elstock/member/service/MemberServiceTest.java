package com.elstock.member.service;

import com.elstock.member.dto.MemberNewDto;
import com.elstock.member.entity.Member;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.io.IOException;

@SpringBootTest
@Transactional // 테스트가 완료되고 나면, 자동으로 롤백이 됩니다.
public class MemberServiceTest {
    @Autowired
    MemberService memberService ;

    @Autowired(required = false) // required 필수 사항인지 여부 확인하기(true: 필수, false: 필수X)
    PasswordEncoder passEncoder ; // null 이기 때문에 외부에서 가져와야함. 주입!하기 (Injection), 의존성(dependency) 반영 하기!

    private Member createMember() throws JSONException, IOException {
        // DTO는 차후에 폼 양식에서 기입하는 내용이 됩니다.
        MemberNewDto dto = new MemberNewDto() ;
        dto.setAddress("마포구 합정동") ;
        dto.setName("김유신") ;
        dto.setPassword("1234") ;
        dto.setEmail("asdf@naver.com") ;

        return Member.createMember(dto, passEncoder) ;
    }

    @Test
    @DisplayName("회원 가입 테스트")
    public void saveMember() throws JSONException, IOException {
        // member는 폼 양식에서 기입한 내용입니다.
        Member member = this.createMember() ;

        // savedMember는 JPA를 이용하여 실제 데이터 베이스에 들어간 내용입니다.
        Member savedMember = this.memberService.saveMember(member) ;

        //p. 60 Assertions.assertEquals(before, after) : 두 개의 값을 비교합니다.
        Assertions.assertEquals(member.getEmail(), savedMember.getEmail());
        Assertions.assertEquals(member.getAddress(), savedMember.getAddress());
        Assertions.assertEquals(member.getName(), savedMember.getName());
        Assertions.assertEquals(member.getRole(), savedMember.getRole());
        Assertions.assertEquals(member.getPassword(), savedMember.getPassword());


    }

    @Test
    @DisplayName("중복 회원 가입 테스트")
    public void saveDuplicateTest() throws JSONException, IOException {
        Member member01 = this.createMember() ; // 이미 가입된 회원 정보
        Member member02 = this.createMember() ; // 이번에 가입할 회원 정보

        this.memberService.saveMember(member01) ;

        Throwable err = Assertions.assertThrows(IllegalStateException.class, () -> {
            this.memberService.saveMember(member02) ;

        }) ;

        System.out.println("예외 메세지 검증하기");
        Assertions.assertEquals("이미 가입된 회원입니다.", err.getMessage());
        err.printStackTrace();

    }



}
