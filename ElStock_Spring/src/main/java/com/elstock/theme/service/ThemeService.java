package com.elstock.theme.service;



import com.elstock.theme.dto.Ticker_by_themeDto;
import com.elstock.theme.entity.Theme;

import com.elstock.theme.entity.Ticker_by_theme;
import com.elstock.theme.repository.themeRepository;
import com.elstock.theme.repository.ticker_by_themeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThemeService {
    private final themeRepository themeRepository;
    private final ticker_by_themeRepository ticker_by_themeRepository;

    //페이지
    public Page<Theme> themeList(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10);

        return themeRepository.findAll(pageable);
    }

    public List<Ticker_by_themeDto> ticker_by_themeList(String th_code){

        List<Ticker_by_themeDto> ticker_by_themeDtos =
                this.ticker_by_themeRepository.findByTicker_by_theme(th_code);

        return ticker_by_themeDtos ;
    }

}
