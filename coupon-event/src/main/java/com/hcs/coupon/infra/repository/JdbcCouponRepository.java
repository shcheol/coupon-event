package com.hcs.coupon.infra.repository;

import com.hcs.coupon.domain.Coupon;

import java.util.List;

public interface JdbcCouponRepository {

    int batchInsert(List<Coupon> coupon);
}
