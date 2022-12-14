package com.elstock.domestick.controller;


import com.elstock.domestic.service.DomesticService;
import com.elstock.domestick.entity.JPY;
import com.elstock.domestick.entity.Kosdaq;
import com.elstock.domestick.entity.Kospi;
import com.elstock.domestick.entity.USD;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/domestic")
@RequiredArgsConstructor
public class domesticController {
    private final DomesticService domesticService;


    // 코스피 지수
    @GetMapping("/information")
    public String kospiList(Model model,
                            @PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Kospi> kospiList =domesticService.getKospiList(pageable);
        Page<Kosdaq> kosdaqList =domesticService.getKosdaqList(pageable);
        Page<USD> usdList=domesticService.getUsdList(pageable);
        Page<JPY> jpyList=domesticService.getJpyList(pageable);

        //1을 더해주는 이유는 pageable은 0부터라 1을 처리하려면 1을 더해서 시작해주어야 한다.
        int nowPage = kospiList.getPageable().getPageNumber() + 1;
        //-1값이 들어가는 것을 막기 위해서 max값으로 두 개의 값을 넣고 더 큰 값을 넣어주게 된다.
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 9, kospiList.getTotalPages());

        // 한페이지에 뿌려주기 위해, 같은 메소드 안에 넣어줌.
        model.addAttribute("kospi", kospiList);
        model.addAttribute("kosdaq", kosdaqList);
        model.addAttribute("USD",usdList);
        model.addAttribute("JPY",jpyList);

        // 페이징
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "domestics/_domestic_detail";

    }
//    @GetMapping("/kosdaq")
//    public String kosdaqList(Model model,
//                            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
//
//        Page<Kosdaq> kosdaqList =domesticService.getKosdaqList(pageable);
//
//        //1을 더해주는 이유는 pageable은 0부터라 1을 처리하려면 1을 더해서 시작해주어야 한다.
//        int nowPage = kosdaqList.getPageable().getPageNumber() + 1;
//        //-1값이 들어가는 것을 막기 위해서 max값으로 두 개의 값을 넣고 더 큰 값을 넣어주게 된다.
//        int startPage = Math.max(nowPage - 4, 1);
//        int endPage = Math.min(nowPage + 9, kosdaqList.getTotalPages());
//
//        model.addAttribute("kosdaq", kosdaqList);
//        model.addAttribute("nowPage", nowPage);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//
//        return "domestic/kosdaq";
//
//    }



}
