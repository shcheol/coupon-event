package com.hcs.promotion.dto;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * com.hcs.promotion.dto.QPromotionDto is a Querydsl Projection type for PromotionDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QPromotionDto extends ConstructorExpression<PromotionDto> {

    private static final long serialVersionUID = 2143075033L;

    public QPromotionDto(com.querydsl.core.types.Expression<? extends com.hcs.promotion.domain.PromotionId> promotionId, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> context, com.querydsl.core.types.Expression<Integer> quantity, com.querydsl.core.types.Expression<? extends com.hcs.coupon.domain.DiscountPolicy> discountPolicy, com.querydsl.core.types.Expression<? extends com.hcs.promotion.domain.PromotionPeriod> period) {
        super(PromotionDto.class, new Class<?>[]{com.hcs.promotion.domain.PromotionId.class, String.class, String.class, int.class, com.hcs.coupon.domain.DiscountPolicy.class, com.hcs.promotion.domain.PromotionPeriod.class}, promotionId, title, context, quantity, discountPolicy, period);
    }

}

