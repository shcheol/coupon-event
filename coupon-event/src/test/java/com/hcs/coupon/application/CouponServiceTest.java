package com.hcs.coupon.application;

import com.hcs.common.exception.CouponException;
import com.hcs.coupon.domain.Coupon;
import com.hcs.coupon.domain.CouponId;
import com.hcs.coupon.domain.CouponIssuedEvent;
import com.hcs.coupon.domain.CouponState;
import com.hcs.coupon.infra.repository.CouponRepository;
import com.hcs.member.MemberId;
import com.hcs.promotion.domain.PromotionId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CouponServiceTest {

    @Autowired
    CouponService service;

    @Autowired
    CouponRepository repository;

    @Test
    @DisplayName("쿠폰발급")
    void issue() {
        CouponId couponId = service.issue(new CouponIssuedEvent("1", "promotion2"));

        Coupon coupon = repository.findById(couponId).get();
        assertThat(coupon.getIssuedDate()).isNotNull();
        assertThat(coupon.getMemberId()).isEqualTo(MemberId.of("1"));
        assertThat(coupon.getState()).isEqualTo(CouponState.ISSUED);
    }

    @Test
    @DisplayName("쿠폰발급 실패 수량부족")
    void issueCouponFail1() {
        service.issue(new CouponIssuedEvent("1", "promotion2"));
        service.issue(new CouponIssuedEvent("2", "promotion2"));

        assertThrows(CouponException.class,
                () -> service.issue(new CouponIssuedEvent("3", "promotion2")));
    }

    @Test
    @DisplayName("쿠폰발급 실패 중복참여")
    void issueCouponFail2() {
        service.issue(new CouponIssuedEvent("1", "promotion2"));

        assertThrows(CouponException.class,
                () -> service.issue(new CouponIssuedEvent("1", "promotion2")));
    }


}