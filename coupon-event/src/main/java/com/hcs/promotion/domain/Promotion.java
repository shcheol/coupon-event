package com.hcs.promotion.domain;

import com.hcs.coupon.domain.DiscountPolicy;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "promotion")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Promotion {

    @EmbeddedId
    private PromotionId promotionId;
    @Nonnull
    private String title;

    private String context;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private DiscountPolicy discountPolicy;

    @Embedded
    private PromotionPeriod period;

    public static Promotion create(String title, String context, int quantity, DiscountPolicy discountPolicy, PromotionPeriod period) {
        Promotion promotion = new Promotion();
        promotion.setPromotionId(PromotionId.newId());
        promotion.setTitle(title);
        promotion.setContext(context);
        promotion.setQuantity(quantity);
        promotion.setDiscountPolicy(discountPolicy);
        promotion.setPeriod(period);

        return promotion;
    }

    private void setPromotionId(PromotionId promotionId) {
        this.promotionId = promotionId;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private void setContext(String context) {
        this.context = context;
    }

    private void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }

    private void setPeriod(PromotionPeriod period) {
        this.period = period;
    }
}
