package com.elstock.common.controller;

import com.elstock.common.service.CommonService;
import com.elstock.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CommonController {

    private final CommonService commonService;

    public String commonData(Principal principal, Model model){
        System.out.println("commonData 들어가기 전");
        String email = principal.getName();
        Member member = this.commonService.getMemberInfo(email);

        model.addAttribute("member", member);

        System.out.println("commonData");
        System.out.println(member.toString());

        return "partials/_navbar";
    }


}
