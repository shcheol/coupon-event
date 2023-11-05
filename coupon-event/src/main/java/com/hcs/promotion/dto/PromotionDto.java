package com.hcs.promotion.dto;

import com.hcs.coupon.domain.DiscountPolicy;
import com.hcs.promotion.domain.Promotion;
import com.hcs.promotion.domain.PromotionId;
import com.hcs.promotion.domain.PromotionPeriod;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PromotionDto {

	private final String promotionId;

	private final String title;

	private final String context;

	private final int quantity;

	private final DiscountPolicy discountPolicy;

	private final PromotionPeriod period;

	@QueryProjection
	public PromotionDto(PromotionId promotionId, String title, String context, int quantity, DiscountPolicy discountPolicy, PromotionPeriod period) {
		this.promotionId = promotionId.getId();
		this.title = title;
		this.context = context;
		this.quantity = quantity;
		this.discountPolicy = discountPolicy;
		this.period = period;
	}

	public static PromotionDto convert(Promotion promotion) {
		return new PromotionDto(promotion.getPromotionId(),
				promotion.getTitle(),
				promotion.getContext(),
				promotion.getQuantity(),
				promotion.getDiscountPolicy(),
				promotion.getPeriod());
	}
}
