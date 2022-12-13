package com.elstock.theme.controller;



import com.elstock.theme.service.ThemeService;
import com.elstock.theme.dto.Ticker_by_themeDto;
import com.elstock.theme.entity.Theme;
import com.elstock.theme.entity.Ticker_by_theme;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/theme")
public class ThemeController {

    private final ThemeService themeService;


//    테마
    @GetMapping("/list")
    public String themeList(Model model,
                            @PageableDefault(page = 0, size = 10) Pageable pageable) {

        Page<Theme> themeList =themeService.themeList(pageable);
        //1을 더해주는 이유는 pageable은 0부터라 1을 처리하려면 1을 더해서 시작해주어야 한다.

        System.out.println("themeList.toString()");
        System.out.println(themeList.toString());

        int nowPage = themeList.getPageable().getPageNumber() + 1;
        //-1값이 들어가는 것을 막기 위해서 max값으로 두 개의 값을 넣고 더 큰 값을 넣어주게 된다.
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 9, themeList.getTotalPages());

        model.addAttribute("theme", themeList);

        // 페이징
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "theme/list";

    }

    @GetMapping(value="detail/{th_code}")
    public String tickerDetail(@PathVariable("th_code") String th_code,Model model){

        List<Ticker_by_themeDto> ticker_by_themeDtos =
                this.themeService.ticker_by_themeList(th_code);


//        System.out.println(ticker_by_themeDtos.toString());
        model.addAttribute("tickerlist", ticker_by_themeDtos) ;

        return "theme/detail" ;
    }



}
