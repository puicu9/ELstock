package com.elstock.theme.dto;

import com.elstock.theme.entity.Ticker_by_theme;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.modelmapper.ModelMapper;

@Getter @Setter @ToString
public class Ticker_by_themeDto {

    private Long id;

    private String th_code;
    private String ticker_code;
    private String ticker_name;

    public Ticker_by_themeDto(Long id, String th_code, String ticker_code, String ticker_name) {
        this.id = id;
        this.th_code = th_code;
        this.ticker_code = ticker_code;
        this.ticker_name = ticker_name;
    }

    public Ticker_by_themeDto() {
    }
    private static ModelMapper modelMapper = new ModelMapper() ;

    public static Ticker_by_themeDto of(Ticker_by_theme ticker_by_theme) {
        // (Entity) 객체의 정보를 ProductFormDto(Dto)에 맵핑해줍니다.
        return modelMapper.map(ticker_by_theme, Ticker_by_themeDto.class);
    }
}
