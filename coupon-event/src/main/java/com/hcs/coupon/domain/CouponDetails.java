package com.hcs.coupon.domain;

import com.hcs.promotion.domain.PromotionId;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CouponDetails {

    @Embedded
    private PromotionId promotionId;

    private LocalDateTime duringDate;

    @Column(name = "discount_policy")
    @Enumerated(EnumType.STRING)
    private DiscountPolicy discountPolicy;


}
