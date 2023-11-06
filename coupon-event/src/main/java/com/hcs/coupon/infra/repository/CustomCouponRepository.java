package com.hcs.coupon.infra.repository;

import com.hcs.coupon.domain.CouponId;
import com.hcs.coupon.dto.CouponDto;
import com.hcs.coupon.dto.CouponSearchCondition;
import com.hcs.member.MemberId;

import java.util.List;

public interface CustomCouponRepository {

    List<CouponId> findCouponsInPromotion(CouponSearchCondition condition);

	List<CouponDto> findMyCoupons(String memberId);

    CouponId findAssignedCoupon(CouponSearchCondition condition);
}
