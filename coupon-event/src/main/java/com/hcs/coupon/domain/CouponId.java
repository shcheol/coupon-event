package com.hcs.coupon.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@ToString
@Getter
public class CouponId implements Serializable {

    @Column(name = "coupon_id")
    private String id;

    public static CouponId newId() {
        CouponId id = new CouponId();
        id.id = UUID.randomUUID().toString();
        return id;
    }

    public static CouponId of(String id){
        CouponId couponId = new CouponId();
        couponId.id = id;
        return couponId;
    }

}
