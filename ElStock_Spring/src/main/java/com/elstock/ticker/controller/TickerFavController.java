//package com.elstock.ticker.controller;
//
//import com.elstock.ticker.dto.TickerFavDto;
////import com.elstock.ticker.service.TickerFavService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.validation.Valid;
//import java.security.Principal;
//import java.util.List;
//
//@Controller
//@RequiredArgsConstructor
//public class TickerFavController {
//
//    private final TickerFavService tickerFavService;
//
//    @PostMapping(value = "/favor")
//    public @ResponseBody ResponseEntity addFavor(
//            @RequestBody @Valid TickerFavDto tickerFavDto,
//            BindingResult error,
//            Principal principal
//    ){
//
//        if(error.hasErrors()){
//            StringBuilder sb = new StringBuilder();
//            List<FieldError> fieldErrors = error.getFieldErrors();
//            for (FieldError fe : fieldErrors){
//                sb.append(fe.getDefaultMessage());
//            }
//            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
//        }
//
//        String email = principal.getName();
//        try {
//            String fav_id = this.tickerFavService.addFavor(tickerFavDto, email);
//            return new ResponseEntity<String>(fav_id, HttpStatus.OK);
//        } catch (Exception err){
//            err.printStackTrace();
//            return new ResponseEntity<String>(err.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }
//
//
//    @GetMapping(value="/favor") // TickerFavController02
//    public String favorHit(Principal principal, Model model){
//        String email = principal.getName();
//        List<TickerFavDto> tickerFavDtoList = this.tickerFavService.getFavorList(email);
//        model.addAttribute("tickerFavDtoList",tickerFavDtoList);
//        return "partials/_navbar_1206";
//    }
//
//}
