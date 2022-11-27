package com.elstock.member.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

// DTO (Data Transfer Object) 유사한 개념 3가지: Bean Dto VO
// cf) VO(Value Object), Java Bean
// 회원 가입 화면(<form> 태그)에서 넘겨지는(transfer) 파라미터를 저장할 Dto 클래스
@Getter @Setter @ToString
public class MemberNewDto {
    @NotBlank(message = "이름은 필수입력 사항입니다.")
    private String name ;

    @NotEmpty(message = "이메일은 필수 입력 사항입니다.")
    @Email(message = "올바른 메일 형식이 아닙니다.")
    private String email ;

    @NotEmpty(message = "비밀번호는 필수 입력 사항입니다.")
    @Length(min = 8, max = 16, message = "비밀번호는 8자리 이상 16자리 이하로 입력해 주세요.")
    private String password ;

    @NotEmpty(message = "주소는 필수 입력 사항입니다.")
    private String address ;
}
