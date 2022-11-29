package com.elstock.member.entity;

import com.elstock.constant.Role;
import com.elstock.member.dto.MemberNewDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.json.JSONException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.io.IOException;
import java.time.LocalDateTime;

@Entity
@Table(name = "members")
@Getter @Setter @ToString
public class Member {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;
    private String password;
    private String address;
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Role role; // 일반 사용자, 관리자 모드 구분

    private LocalDateTime regDate;
    private int f_balance;


    // 폼 화면에서 넘어오는 dto 객체를 이용하여 해당 회원에 대한 비번번호의 암호화를 처리해주는 메소드입니다.


    public static Member createMember(MemberNewDto dto, PasswordEncoder passEncoder) throws JSONException, IOException {
        Member member = new Member();

        member.setName(dto.getName());
        member.setAddress(dto.getAddress());
        member.setEmail(dto.getEmail());
        member.setRole(Role.USER); // 차후 관리자와 구분이 필요할 듯 ...
        // member.setId();

        GetNickname getNickname = new GetNickname();
        String nickname = getNickname.Nickname();
        member.setNickname(nickname);


        String password = passEncoder.encode(dto.getPassword()); // 비번 암호화
        member.setPassword(password);
        member.setF_balance(100000000);

        return member;
    }
}
