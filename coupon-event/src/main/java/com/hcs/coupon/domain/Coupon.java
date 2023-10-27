package com.hcs.coupon.domain;

import com.hcs.couponevent.domain.DiscountPolicy;
import com.hcs.couponevent.domain.CouponEventId;
import com.hcs.member.MemberId;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="coupon")
public class Coupon {

    @EmbeddedId
    private CouponId couponId;

    @Embedded
    private CouponEventId couponEventId;

    private LocalDateTime duringDate;

    private LocalDateTime issuedDate;

    @Column(name = "discount_policy")
    @Enumerated(EnumType.STRING)
    private DiscountPolicy discountPolicy;

    @Embedded
    private MemberId memberId;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private CouponState state;

}
