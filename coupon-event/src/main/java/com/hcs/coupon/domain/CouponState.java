package com.hcs.coupon.domain;

import lombok.Getter;

@Getter
public enum CouponState {

    CREATED, ISSUED, USED, EXPIRED,
}
