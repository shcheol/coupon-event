package com.hcs.promotion.domain;

import com.hcs.common.event.Event;
import com.hcs.coupon.domain.CouponDetails;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PromotionCreatedEvent extends Event {

    private PromotionId promotionId;

    private int quantity;

    private CouponDetails details;
}
