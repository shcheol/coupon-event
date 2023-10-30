package com.hcs.promotion.dto;

import com.hcs.coupon.domain.DiscountPolicy;
import com.hcs.promotion.domain.PromotionId;
import com.hcs.promotion.domain.PromotionPeriod;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PromotionDto {

	private PromotionId promotionId;

	private String title;

	private String context;

	private int quantity;

	private DiscountPolicy discountPolicy;

	private PromotionPeriod period;

	@QueryProjection
	public PromotionDto(PromotionId promotionId, String title, String context, int quantity, DiscountPolicy discountPolicy, PromotionPeriod period) {
		this.promotionId = promotionId;
		this.title = title;
		this.context = context;
		this.quantity = quantity;
		this.discountPolicy = discountPolicy;
		this.period = period;
	}
}
