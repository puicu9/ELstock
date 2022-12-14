package com.elstock.theme.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter @Setter @ToString
@Entity(name = "themes")
public class Theme {


    @Id
    private Long id;

    // 테마 코드
    private String th_code;
    // 테마명
    private String th_name;
    // 전날대비
    private String th_updown;
    // 3일평균
    private String th_avg_3;


}
