package com.hcs.coupon.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCouponDetails is a Querydsl query type for CouponDetails
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QCouponDetails extends BeanPath<CouponDetails> {

    private static final long serialVersionUID = -978102857L;

    public static final QCouponDetails couponDetails = new QCouponDetails("couponDetails");

    public final EnumPath<DiscountPolicy> discountPolicy = createEnum("discountPolicy", DiscountPolicy.class);

    public final DateTimePath<java.time.LocalDateTime> duringDate = createDateTime("duringDate", java.time.LocalDateTime.class);

    public QCouponDetails(String variable) {
        super(CouponDetails.class, forVariable(variable));
    }

    public QCouponDetails(Path<? extends CouponDetails> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCouponDetails(PathMetadata metadata) {
        super(CouponDetails.class, metadata);
    }

}

