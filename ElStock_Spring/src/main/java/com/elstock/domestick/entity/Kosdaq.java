package com.elstock.domestick.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@Entity(name = "kosdaqs")
public class Kosdaq {

    @Id
    private Long id;
    private String kosdaq_date;
    private String kosdaq_index;
}
