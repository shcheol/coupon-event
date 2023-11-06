package com.hcs.promotion.application;

import com.hcs.coupon.domain.CouponDetails;
import com.hcs.coupon.domain.DiscountPolicy;
import com.hcs.promotion.domain.Promotion;
import com.hcs.promotion.domain.PromotionId;
import com.hcs.promotion.domain.PromotionPeriod;
import com.hcs.promotion.dto.CreatePromotionRequest;
import com.hcs.promotion.dto.PromotionDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
class PromotionServiceTest {

    @Autowired
    private PromotionService promotionService;


    @Test
    @Commit
    void create() {
        CreatePromotionRequest createPromotionRequest = new CreatePromotionRequest("title", "context", 500, DiscountPolicy.TEN_PERCENTAGE,
                new PromotionPeriod(LocalDateTime.now(), LocalDateTime.of(2024, 10, 28, 00, 00)),
						new CouponDetails(LocalDateTime.of(2024, 10, 28, 00, 00), DiscountPolicy.TEN_PERCENTAGE));
        PromotionDto promotion = promotionService.create(createPromotionRequest);
        PromotionDto promotionDto = promotionService.findByPromotionId(promotion.getPromotionId());
        assertThat(promotion.getPromotionId()).isEqualTo(PromotionId.of(promotionDto.getPromotionId()));
        assertThat(promotion.getTitle()).isEqualTo(promotionDto.getTitle());
    }
}