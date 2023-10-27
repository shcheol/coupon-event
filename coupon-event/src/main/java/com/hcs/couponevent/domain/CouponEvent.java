package com.hcs.couponevent.domain;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CouponEvent {

    @EmbeddedId
    private CouponEventId couponEventId;

    @Nonnull
    private String title;

    private String context;

    private int quality;

    @Enumerated(EnumType.STRING)
    private DiscountPolicy discountPolicy;

    @Embedded
    private CouponEventPeriod period;

    public static CouponEvent create(String title, String context, int quality, DiscountPolicy discountPolicy, CouponEventPeriod period) {
        CouponEvent couponEvent = new CouponEvent();
        couponEvent.setCouponEventId(CouponEventId.newId());
        couponEvent.setTitle(title);
        couponEvent.setContext(context);
        couponEvent.setQuality(quality);
        couponEvent.setDiscountPolicy(discountPolicy);
        couponEvent.setPeriod(period);
        return couponEvent;
    }

    private void setCouponEventId(CouponEventId couponEventId) {
        this.couponEventId = couponEventId;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setContext(String context) {
        this.context = context;
    }

    private void setQuality(int quality) {
        this.quality = quality;
    }

    private void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    private void setPeriod(CouponEventPeriod period) {
        this.period = period;
    }
}
