package com.hcs.promotion.application;

import com.hcs.coupon.domain.CouponDetails;
import com.hcs.coupon.domain.DiscountPolicy;
import com.hcs.promotion.domain.PromotionPeriod;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreatePromotionRequest {
    private String title;

    private String context;

    private int quality;

    private DiscountPolicy discountPolicy;

    private PromotionPeriod period;

//    private CouponDetails details;
}
