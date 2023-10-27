package com.hcs.couponevent.domain;

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
public class CouponEventId implements Serializable {

    @Column(name = "coupon_event_id")
    private String id;

    public static CouponEventId newId() {
        CouponEventId id = new CouponEventId();
        id.id = UUID.randomUUID().toString();
        return id;
    }

}
