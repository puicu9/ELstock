package com.elstock.ticker.controller;

import com.elstock.ticker.dto.TickerFavDto;
import com.elstock.ticker.service.TickerFavService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TickerFavController {

    private final TickerFavService tickerFavService;

    @GetMapping(value="/favor") // TickerFavController02
    public String favorHit(Principal principal, Model model){
        String email = principal.getName();
        List<TickerFavDto> tickerFavDtoList = this.tickerFavService.getFavorList(email);
        model.addAttribute("tickerFavDtoList",tickerFavDtoList);
        return "partials/_navbar";
    }

}
