package com.elstock.domestick.repository;


import com.elstock.domestick.entity.Kosdaq;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KosdaqRepository extends JpaRepository<Kosdaq,Long> {
}
