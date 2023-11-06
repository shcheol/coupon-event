package com.hcs.promotion.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPromotionId is a Querydsl query type for PromotionId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QPromotionId extends BeanPath<PromotionId> {

    private static final long serialVersionUID = 966979618L;

    public static final QPromotionId promotionId = new QPromotionId("promotionId");

    public final StringPath id = createString("id");

    public QPromotionId(String variable) {
        super(PromotionId.class, forVariable(variable));
    }

    public QPromotionId(Path<? extends PromotionId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPromotionId(PathMetadata metadata) {
        super(PromotionId.class, metadata);
    }

}

