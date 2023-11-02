package com.hcs.promotion.application;

import com.hcs.coupon.domain.DiscountPolicy;
import com.hcs.promotion.domain.Promotion;
import com.hcs.promotion.domain.PromotionId;
import com.hcs.promotion.domain.PromotionPeriod;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.time.LocalDateTime;


@SpringBootTest
class PromotionServiceTest {

    @Autowired
    private PromotionService promotionService;


    @Test
    @Commit
    void create() {
        CreatePromotionRequest createPromotionRequest = new CreatePromotionRequest("title", "context", 10, DiscountPolicy.TEN_PERCENTAGE,
                new PromotionPeriod(LocalDateTime.now(), LocalDateTime.of(2024, 10, 28, 00, 00))
        );
        Promotion promotion = promotionService.create(createPromotionRequest);
        Assertions.assertThat(promotion).isEqualTo(promotionService.findByPromotionId(promotion.getPromotionId().toString()));
    }
}