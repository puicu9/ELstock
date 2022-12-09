//package com.elstock.ticker.repository;
//
//import com.elstock.ticker.entity.Favor;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface TickerRepository extends JpaRepository<Favor,String > {
//    // 멤버와 즐겨찾기는 일대일 연관 관계 맵핑을 맺고 있음.
//    // 로그인 한 회원의 카트 정보를 조회
//    Favor findByMemberId(String email);
//}
