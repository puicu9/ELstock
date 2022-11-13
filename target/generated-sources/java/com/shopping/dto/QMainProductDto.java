package com.shopping.dto;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.shopping.dto.QMainProductDto is a Querydsl Projection type for MainProductDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QMainProductDto extends ConstructorExpression<MainProductDto> {

    private static final long serialVersionUID = 473483377L;

    public QMainProductDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> description, com.querydsl.core.types.Expression<String> imageUrl, com.querydsl.core.types.Expression<Integer> price) {
        super(MainProductDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, int.class}, id, name, description, imageUrl, price);
    }

}

