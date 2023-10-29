package com.hcs.coupon.domain;

import com.hcs.common.exception.CouponError;
import com.hcs.common.exception.CouponException;
import com.hcs.member.MemberId;
import com.hcs.promotion.domain.PromotionId;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="coupon")
public class Coupon {

    @EmbeddedId
    private CouponId couponId;

    @Embedded
    private PromotionId promotionId;
    private LocalDateTime issuedDate;
    @Embedded
    private MemberId memberId;
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private CouponState state;
    @Embedded
    private CouponDetails details;

    public static Coupon create(PromotionId promotionId, CouponDetails details){
        Coupon coupon = new Coupon();
        coupon.couponId = CouponId.newId();
        coupon.promotionId = promotionId;
        coupon.details = details;
        coupon.state = CouponState.CREATED;
        return coupon;
    }

    public static List<Coupon> createAll(PromotionId promotionId, int quantity, CouponDetails details){
        if (quantity<=0) throw new CouponException(CouponError.INVALID_QUANTITY);
        ArrayList<Coupon> coupons = new ArrayList<>();
        for (int i=0;i<quantity;i++) {
            coupons.add(Coupon.create(promotionId, details));
        }
        return coupons;
    }

    public void issuedCoupon(MemberId memberId){
        this.memberId = memberId;
        this.issuedDate = LocalDateTime.now();
        this.state = CouponState.ISSUED;
    }

}
