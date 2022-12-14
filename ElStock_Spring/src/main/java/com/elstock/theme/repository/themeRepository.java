package com.elstock.theme.repository;

import com.elstock.theme.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface themeRepository extends JpaRepository<Theme,Long> {

}
