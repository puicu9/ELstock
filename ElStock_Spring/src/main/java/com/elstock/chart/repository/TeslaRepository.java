package com.elstock.chart.repository;

import com.elstock.chart.entity.Tesla;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeslaRepository extends JpaRepository<Tesla, Long>{
    List<Tesla> findAllByOrderByIdDesc();

}
