package com.elstock.member.controller;

import com.elstock.member.dto.MemberNewDto;
import com.elstock.member.entity.Member;
import com.elstock.member.service.MemberService;
import lombok.RequiredArgsConstructor;


import org.json.JSONException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequiredArgsConstructor // for MemberController02
@RequestMapping(value = "members")
public class MemberController {
    private final String urlPrefix = "/member" ; // MemberController01

    @GetMapping(value = "/new")
    public String insertForm(Model model){ // MemberController01
        model.addAttribute("memberFormDto", new MemberNewDto()) ;
        return urlPrefix + "/memberInsertForm" ;
    }

    // for MemberController02
    private final MemberService memberService ;
    private final PasswordEncoder passEncoder ;


    // @Valid 는 command 객체에 유효성 검사를 진행해 줍니다.
    // BindingResult 는 유효성 검사에 문제가 있으면, 해당 정보가 들어 있습니다.
    @PostMapping(value = "/new")
    public String insertForm2(@Valid MemberNewDto dto, BindingResult error, Model model){ // MemberController02
        if(error.hasErrors()){ // 유효성 검사를 충족하지 못하면 다시 가입 페이지로 이동
            return urlPrefix + "/memberInsertForm" ;
        }

        try{
            Member member = Member.createMember(dto, passEncoder) ;
            this.memberService.saveMember(member) ;
        } catch (IllegalStateException err){
            model.addAttribute("errorMessage", err.getMessage()) ;
            return urlPrefix + "/memberInsertForm" ;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 메인 페이지로 이동
        return "redirect:/" ; // response.sendRedirect() ; 와 같음
    }


    // MemberController03
    // form 태그와 SecurityConfig 파일에 정의되어 있습니다.
    @GetMapping(value = "/login")
    public String loginMember(){
        return "/member/memberLoginForm" ;
    }

    // MemberController03
    // SecurityConfig 파일에 정의되어 있습니다.
    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        // "loginErrorMsg" 내용은 memberLoginForm.html 파일 내에 구현되어 있습니다.
        model.addAttribute("loginErrorMsg", "이메일 또는 비밀번호를 확인해 주세요") ;
        return "/member/memberLoginForm" ;
    }


}
