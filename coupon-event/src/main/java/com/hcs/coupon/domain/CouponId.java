package com.hcs.coupon.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@ToString
public class CouponId implements Serializable {

    @Column(name = "coupon_id")
    private String id;

    public static CouponId newId() {
        CouponId id = new CouponId();
        id.id = UUID.randomUUID().toString();
        return id;
    }

}
