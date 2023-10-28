package com.hcs.coupon.domain;

import com.hcs.member.MemberId;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="coupon")
public class Coupon {

    @EmbeddedId
    private CouponId couponId;
    private LocalDateTime issuedDate;
    @Embedded
    private MemberId memberId;
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private CouponState state;
    @Embedded
    private CouponDetails details;
    public static Coupon create(CouponDetails details){
        Coupon coupon = new Coupon();
        coupon.details = details;
        coupon.state = CouponState.CREATED;
        return coupon;
    }

    public void issuedCoupon(MemberId memberId){
        this.memberId = memberId;
        this.issuedDate = LocalDateTime.now();
        this.state = CouponState.ISSUED;
    }

}
