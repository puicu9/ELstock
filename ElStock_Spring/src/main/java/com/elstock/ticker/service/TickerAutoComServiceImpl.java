package com.elstock.ticker.service;

import com.elstock.ticker.mapper.AutoComMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TickerAutoComServiceImpl implements TickerAutoComService{
    @Autowired
    AutoComMapper autoComMapper ;

    @Override
    public List<Map<String, Object>> autocomplete(Map<String, Object> paramMap) throws Exception {
        return autoComMapper.autocomplete(paramMap);
    }
}
