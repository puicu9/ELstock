//package com.elstock.ticker.service;
//
//import com.elstock.market.entity.Market;
//import com.elstock.market.repository.MarketListRepository;
//import com.elstock.member.entity.Member;
//import com.elstock.member.repository.MemberRepository;
//import com.elstock.ticker.dto.TickerFavDto;
//import com.elstock.ticker.entity.Favor;
//import com.elstock.ticker.entity.TickerFavor;
//import com.elstock.ticker.repository.FavorRepository;
//import com.elstock.ticker.repository.TickerFavRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityExistsException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class TickerFavService {
//    private final MemberRepository memberRepository;
//    private final MarketListRepository marketListRepository;
//    private final FavorRepository favorRepository;
//    private final TickerFavRepository tickerFavRepository;
//
//    // 티커코드, 이메일 정보를 이용하여 티커 Entity를 생성합니다.
//    public String addFavor(TickerFavDto tickerFavDto, String email){
//        String favDto_code = tickerFavDto.getFav_code();
//        Market ticker = this.marketListRepository.findById(favDto_code).orElseThrow(EntityExistsException::new);
//
//        Member member = this.memberRepository.findByEmail(email);
//        String memberEmail = member.getEmail();
//
//        Favor favor = this.favorRepository.findByMemberId(memberEmail);
//        System.out.println("favor 값 찾기");
//        System.out.println(favor.toString());
//
//        if(favor == null){
//            favor = Favor.createFavor(member);
//            favorRepository.save(favor);
//        }
//
//        String fav_id = favor.getId();
//        TickerFavor savedTickerFavor = this.tickerFavRepository.findByFavCodeAndTickerCode(fav_id, favDto_code) ;
//
//        if(savedTickerFavor != null ){ // 해당 장바구니에 이미 상품이 들어있는 경우 => 삭제
//            // 장바구니에 있는 아이디와 ~~가 같을 경우 삭제
//
//
//
////            if (savedTickerFavor.getId().equals())
////
////            this.favorRepository.delete();
////            this.tickerFavRepository.save(savedTickerFavor);
//        }
//
//
//        //신규 상품을 장바구니에 담는 경우
//        TickerFavor tickerFavor = TickerFavor.createTickerFavor(favor, ticker);
//        System.out.println("tickerFavor.getId()");
//        System.out.println(tickerFavor.getId());
//
//        return tickerFavor.getId();
//    }
//
//    @Transactional(readOnly = true)
//    public List<TickerFavDto> getFavorList(String email){
//        // 현재 0개 들어 있음
//        List<TickerFavDto> tickerFavDtoList = new ArrayList<>();
//
//        Member member = this.memberRepository.findByEmail(email);
//        String memberId = member.getId();
//
//        //로그인 한 사람 즐겨찾기 목록
//        Favor favor = this.favorRepository.findByMemberId(memberId);
//
//        if(favor != null){
//            String favorId = favor.getId();
//            tickerFavDtoList = this.tickerFavRepository.findByFavDtoList(favorId);
//        }
//
//        return tickerFavDtoList ;
//    }
//
//
//
//}
