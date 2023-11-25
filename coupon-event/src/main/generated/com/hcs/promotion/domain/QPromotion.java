package com.hcs.promotion.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPromotion is a Querydsl query type for Promotion
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPromotion extends EntityPathBase<Promotion> {

    private static final long serialVersionUID = 1328379047L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPromotion promotion = new QPromotion("promotion");

    public final StringPath context = createString("context");

    public final EnumPath<com.hcs.coupon.domain.DiscountPolicy> discountPolicy = createEnum("discountPolicy", com.hcs.coupon.domain.DiscountPolicy.class);

    public final QPromotionPeriod period;

    public final QPromotionId promotionId;

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public final StringPath title = createString("title");

    public QPromotion(String variable) {
        this(Promotion.class, forVariable(variable), INITS);
    }

    public QPromotion(Path<? extends Promotion> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPromotion(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPromotion(PathMetadata metadata, PathInits inits) {
        this(Promotion.class, metadata, inits);
    }

    public QPromotion(Class<? extends Promotion> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.period = inits.isInitialized("period") ? new QPromotionPeriod(forProperty("period")) : null;
        this.promotionId = inits.isInitialized("promotionId") ? new QPromotionId(forProperty("promotionId")) : null;
    }

}

