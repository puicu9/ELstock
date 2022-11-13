package com.shopping.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.elstock.entity.Saram;
import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSaram is a Querydsl query type for Saram
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSaram extends EntityPathBase<Saram> {

    private static final long serialVersionUID = 665440062L;

    public static final QSaram saram = new QSaram("saram");

    public final StringPath address = createString("address");

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final NumberPath<Integer> salary = createNumber("salary", Integer.class);

    public QSaram(String variable) {
        super(Saram.class, forVariable(variable));
    }

    public QSaram(Path<? extends Saram> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSaram(PathMetadata metadata) {
        super(Saram.class, metadata);
    }

}

