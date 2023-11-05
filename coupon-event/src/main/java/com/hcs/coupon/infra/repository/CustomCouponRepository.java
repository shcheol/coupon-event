package com.hcs.coupon.infra.repository;

import com.hcs.coupon.domain.CouponId;
import com.hcs.coupon.dto.CouponSearchCondition;

import java.util.List;

public interface CustomCouponRepository {

    List<CouponId> findCouponsInPromotion(CouponSearchCondition condition);

    CouponId findCouponWithMember(CouponSearchCondition condition);
}
