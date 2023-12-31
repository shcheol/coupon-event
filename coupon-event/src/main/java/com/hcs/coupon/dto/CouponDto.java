package com.hcs.coupon.dto;

import com.hcs.coupon.domain.Coupon;
import com.hcs.coupon.domain.CouponDetails;
import com.hcs.coupon.domain.CouponId;
import com.hcs.coupon.domain.CouponState;
import com.hcs.member.MemberId;
import com.hcs.promotion.domain.Promotion;
import com.hcs.promotion.domain.PromotionId;
import com.hcs.promotion.dto.PromotionDto;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CouponDto {
    private final String couponId;
    private final String promotionId;
    private final LocalDateTime issuedDate;
    private final String memberId;
    private final CouponState state;
    private final CouponDetails details;

    @QueryProjection
    public CouponDto(CouponId couponId, PromotionId promotionId, LocalDateTime issuedDate, MemberId memberId, CouponState state, CouponDetails details) {
        this.couponId = couponId.getId();
        this.promotionId = promotionId.getId();
        this.issuedDate = issuedDate;
        this.memberId = memberId.getId();
        this.state = state;
        this.details = details;
    }

	public static CouponDto convert(Coupon coupon) {
		return new CouponDto(
				coupon.getCouponId(),
				coupon.getPromotionId(),
				coupon.getIssuedDate(),
				coupon.getMemberId(),
				coupon.getState(),
				coupon.getDetails()
		);
	}
}
