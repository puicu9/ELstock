package com.elstock.domestic.service;


import com.elstock.domestick.entity.JPY;
import com.elstock.domestick.entity.Kosdaq;
import com.elstock.domestick.entity.Kospi;
import com.elstock.domestick.entity.USD;
import com.elstock.domestick.repository.JpyRepository;
import com.elstock.domestick.repository.KosdaqRepository;
import com.elstock.domestick.repository.KospiRepository;
import com.elstock.domestick.repository.UsdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DomesticService {
    private final KospiRepository kospiRepository;
    private final KosdaqRepository kosdaqRepository;
    private final UsdRepository usdRepository;
    private final JpyRepository jpyRepository;

    public Page<Kospi> getKospiList(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 5);

        return kospiRepository.findAll(pageable);
    }

    public Page<Kosdaq> getKosdaqList(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 5);

        return kosdaqRepository.findAll(pageable);
    }

    public Page<USD> getUsdList(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 5);

        return usdRepository.findAll(pageable);
    }
    public Page<JPY> getJpyList(Pageable pageable){
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 5);

        return jpyRepository.findAll(pageable);
    }
}
