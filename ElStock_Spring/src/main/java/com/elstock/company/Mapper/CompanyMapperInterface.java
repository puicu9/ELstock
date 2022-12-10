package com.elstock.company.Mapper;

import com.elstock.company.entity.Company;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper // 스프링이 자동으로 객체를 생성하고, sql 구문을 분석해줍니다.
public interface CompanyMapperInterface {
    @Select(" select * from companys order by company_rank asc ")
    List<Company> SelectAll() ;
}
