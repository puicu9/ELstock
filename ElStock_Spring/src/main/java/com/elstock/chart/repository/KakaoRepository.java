package com.elstock.chart.repository;

import com.elstock.chart.entity.Kakao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KakaoRepository extends JpaRepository<Kakao, Long>{
    List<Kakao> findAllByOrderByIdDesc();
}
