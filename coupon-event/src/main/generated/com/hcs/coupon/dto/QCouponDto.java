package com.hcs.coupon.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hcs.coupon.dto.QCouponDto is a Querydsl Projection type for CouponDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QCouponDto extends ConstructorExpression<CouponDto> {

    private static final long serialVersionUID = 295852997L;

    public QCouponDto(com.querydsl.core.types.Expression<? extends com.hcs.coupon.domain.CouponId> couponId, com.querydsl.core.types.Expression<? extends com.hcs.promotion.domain.PromotionId> promotionId, com.querydsl.core.types.Expression<java.time.LocalDateTime> issuedDate, com.querydsl.core.types.Expression<? extends com.hcs.member.MemberId> memberId, com.querydsl.core.types.Expression<com.hcs.coupon.domain.CouponState> state, com.querydsl.core.types.Expression<? extends com.hcs.coupon.domain.CouponDetails> details) {
        super(CouponDto.class, new Class<?>[]{com.hcs.coupon.domain.CouponId.class, com.hcs.promotion.domain.PromotionId.class, java.time.LocalDateTime.class, com.hcs.member.MemberId.class, com.hcs.coupon.domain.CouponState.class, com.hcs.coupon.domain.CouponDetails.class}, couponId, promotionId, issuedDate, memberId, state, details);
    }

}

