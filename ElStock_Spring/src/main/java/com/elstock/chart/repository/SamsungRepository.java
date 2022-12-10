package com.elstock.chart.repository;

import com.elstock.chart.entity.Samsung;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SamsungRepository extends JpaRepository<Samsung, Long>{
    List<Samsung> findAllByOrderByIdDesc();
}
