package com.elstock.domestick.repository;


import com.elstock.domestick.entity.Kospi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KospiRepository extends JpaRepository<Kospi,Long> {
}
