package com.hcs.promotion.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPromotionPeriod is a Querydsl query type for PromotionPeriod
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QPromotionPeriod extends BeanPath<PromotionPeriod> {

    private static final long serialVersionUID = -591430328L;

    public static final QPromotionPeriod promotionPeriod = new QPromotionPeriod("promotionPeriod");

    public final DateTimePath<java.time.LocalDateTime> endDate = createDateTime("endDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> startDate = createDateTime("startDate", java.time.LocalDateTime.class);

    public QPromotionPeriod(String variable) {
        super(PromotionPeriod.class, forVariable(variable));
    }

    public QPromotionPeriod(Path<? extends PromotionPeriod> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPromotionPeriod(PathMetadata metadata) {
        super(PromotionPeriod.class, metadata);
    }

}

