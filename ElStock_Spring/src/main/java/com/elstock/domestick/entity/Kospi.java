package com.elstock.domestick.entity;


import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;

@Getter @Setter @ToString
@Entity(name = "kospis")
public class Kospi {

    @Id
    private Long id;

    private String kospi_date;
    private String kospi_index;




}
