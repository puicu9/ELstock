package com.elstock.ticker.service;

import com.elstock.ticker.dto.TickerSearchDto;
import com.elstock.ticker.entity.Market;
import com.elstock.ticker.repository.TickerSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service // Controller가 요청한 내용에 대한 업무 로직을 처리해주는 클래스
@RequiredArgsConstructor // final 또는 @NotNull이 붙어 있는 필드에서 적합한 생성자를 만들어서 데이터를 주입해줍니다
@Transactional // 비즈니스 로직을 처리해주는 서비스 계층에서 오류가 발생 시 로직 수행 이전 상태로 되돌려 줍니다.
public class TickerSearchService { //
    private final TickerSearchRepository tickerSearchRepository ;

    // 검색 조건과 페이징 객체를 이용하여 페이지 결과를 반환
    public Page<Market> getTickerPage(TickerSearchDto dto, Pageable pageable){
        return this.tickerSearchRepository.getTickerPage(dto, pageable);
    }



}
