package com.hcs.coupon.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCoupon is a Querydsl query type for Coupon
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCoupon extends EntityPathBase<Coupon> {

    private static final long serialVersionUID = 229171723L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCoupon coupon = new QCoupon("coupon");

    public final QCouponId couponId;

    public final QCouponDetails details;

    public final DateTimePath<java.time.LocalDateTime> issuedDate = createDateTime("issuedDate", java.time.LocalDateTime.class);

    public final com.hcs.member.QMemberId memberId;

    public final com.hcs.promotion.domain.QPromotionId promotionId;

    public final EnumPath<CouponState> state = createEnum("state", CouponState.class);

    public QCoupon(String variable) {
        this(Coupon.class, forVariable(variable), INITS);
    }

    public QCoupon(Path<? extends Coupon> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCoupon(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCoupon(PathMetadata metadata, PathInits inits) {
        this(Coupon.class, metadata, inits);
    }

    public QCoupon(Class<? extends Coupon> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.couponId = inits.isInitialized("couponId") ? new QCouponId(forProperty("couponId")) : null;
        this.details = inits.isInitialized("details") ? new QCouponDetails(forProperty("details")) : null;
        this.memberId = inits.isInitialized("memberId") ? new com.hcs.member.QMemberId(forProperty("memberId")) : null;
        this.promotionId = inits.isInitialized("promotionId") ? new com.hcs.promotion.domain.QPromotionId(forProperty("promotionId")) : null;
    }

}

