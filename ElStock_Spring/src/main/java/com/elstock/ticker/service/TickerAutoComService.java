package com.elstock.ticker.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface TickerAutoComService {

    List<Map<String, Object>> autocomplete(Map<String, Object> paramMap) throws Exception;
}
