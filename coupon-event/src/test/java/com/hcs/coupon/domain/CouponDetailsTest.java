package com.hcs.coupon.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class CouponDetailsTest {

	LocalDateTime date = LocalDateTime.of(2023, 10, 28, 00, 00);

	@Test
	void equalsAndHashCode(){
		CouponDetails couponDetails1 = new CouponDetails(date, DiscountPolicy.NONE);
		CouponDetails couponDetails2 = new CouponDetails(date, DiscountPolicy.NONE);

		assertEquals(couponDetails1.hashCode(), couponDetails2.hashCode());
		assertEquals(couponDetails1, couponDetails2);
		assertNotSame(couponDetails1, couponDetails2);
	}

}