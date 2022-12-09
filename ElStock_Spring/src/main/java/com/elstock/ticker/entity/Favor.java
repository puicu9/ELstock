//package com.elstock.ticker.entity;
//
//import com.elstock.member.entity.Member;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name="favors")
//@Getter @Setter @ToString
//public class Favor {
//    // 즐겨찾기 담긴
//    @Id
//    @Column(name="fav_id")
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private String id ;
//
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="id")
//    private Member member ;
//
//    // 회원 Entity를 매개 변수로 받아서 장바구니 Entity를 반환해주는 메소드
//    public static Favor createFavor(Member member){
//        Favor favor = new Favor() ;
//        favor.setMember(member) ;
//        return favor ;
//    }
//
//}