package com.hcs.coupon.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCouponId is a Querydsl query type for CouponId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QCouponId extends BeanPath<CouponId> {

    private static final long serialVersionUID = 1190696070L;

    public static final QCouponId couponId = new QCouponId("couponId");

    public final StringPath id = createString("id");

    public QCouponId(String variable) {
        super(CouponId.class, forVariable(variable));
    }

    public QCouponId(Path<? extends CouponId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCouponId(PathMetadata metadata) {
        super(CouponId.class, metadata);
    }

}

