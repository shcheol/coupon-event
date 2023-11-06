package com.hcs.promotion.dto;

import com.hcs.coupon.domain.CouponDetails;
import com.hcs.coupon.domain.DiscountPolicy;
import com.hcs.promotion.domain.PromotionPeriod;

public record CreatePromotionRequest(String title, String context, int quantity, DiscountPolicy discountPolicy,
									 PromotionPeriod period, CouponDetails details) {
}
