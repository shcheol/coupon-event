package com.hcs.promotion.domain;

import com.hcs.common.exception.CouponException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PromotionPeriodTest {

    @Test
    @DisplayName("시작과 종료날짜가 동일")
    void constructPeriodInvalidDate1(){
        assertThrows(CouponException.class, () ->new PromotionPeriod(LocalDateTime.of(2023, 10, 28, 00, 00),
                LocalDateTime.of(2023, 10, 28, 00, 00)));
    }

    @Test
    @DisplayName("종료 날짜가 시작날보다 빠름")
    void constructPeriodInvalidDate2(){
        assertThrows(CouponException.class, () ->new PromotionPeriod(LocalDateTime.of(2023, 10, 28, 00, 00),
                LocalDateTime.of(2022, 10, 28, 00, 00)));
    }

    @Test
    void constructStartDateNull(){
        assertThrows(CouponException.class, () ->new PromotionPeriod(null,
                LocalDateTime.of(2022, 10, 28, 00, 00)));
    }
    @Test
    void constructEndDateNull(){
        assertThrows(CouponException.class, () ->new PromotionPeriod(LocalDateTime.of(2023, 10, 28, 00, 00),
                null));
    }

    @Test
    void equalsAndHashCode(){
        PromotionPeriod period1 = new PromotionPeriod(LocalDateTime.of(2023, 10, 28, 00, 00),
                LocalDateTime.of(2024, 10, 28, 00, 00));
        PromotionPeriod period2 = new PromotionPeriod(LocalDateTime.of(2023, 10, 28, 00, 00),
                LocalDateTime.of(2024, 10, 28, 00, 00));

        assertEquals(period1, period2);
    }
}