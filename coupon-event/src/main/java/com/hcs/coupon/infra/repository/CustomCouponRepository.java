package com.hcs.coupon.infra.repository;

import com.hcs.coupon.domain.Coupon;
import com.hcs.coupon.domain.CouponId;
import com.hcs.coupon.dto.CouponDto;
import com.hcs.coupon.dto.CouponSearchCondition;

import java.util.List;
import java.util.Optional;

public interface CustomCouponRepository {

    Optional<Coupon> findCouponInPromotionAndCanIssue(CouponSearchCondition condition);

	List<CouponId> findCouponsInPromotion(CouponSearchCondition condition);

	List<CouponDto> findMyCoupons(String memberId);

    CouponId findAssignedCoupon(CouponSearchCondition condition);
}
