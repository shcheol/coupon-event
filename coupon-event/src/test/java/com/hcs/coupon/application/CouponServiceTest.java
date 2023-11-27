package com.hcs.coupon.application;

import com.hcs.common.exception.CouponException;
import com.hcs.coupon.domain.*;
import com.hcs.coupon.dto.CouponDto;
import com.hcs.coupon.dto.CouponSearchCondition;
import com.hcs.coupon.infra.repository.CouponRepository;
import com.hcs.member.MemberId;
import com.hcs.promotion.domain.PromotionCreatedEvent;
import com.hcs.promotion.domain.PromotionId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Sql("classpath:test-init.sql")
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
        CouponIssuedEvent failEvent = new CouponIssuedEvent("3", "promotion2");
        assertThrows(CouponException.class,
                () -> service.issue(failEvent));
    }

    @Test
    @DisplayName("쿠폰발급 실패 중복참여")
    void issueCouponFail2() {
        service.issue(new CouponIssuedEvent("1", "promotion2"));
        CouponIssuedEvent failEvent = new CouponIssuedEvent("1", "promotion2");
        assertThrows(CouponException.class,
                () -> service.issue(failEvent));
    }

    @Test
    @DisplayName("insert using JPA saveAll")
    void createAll() {
        PromotionId promotionId = PromotionId.of("promotionTest1");
        service.createAll(new PromotionCreatedEvent(promotionId, 100,
                new CouponDetails(LocalDateTime.of(2024, 10, 28, 00, 00), DiscountPolicy.TEN_PERCENTAGE)));
        assertThat(repository.findCouponsInPromotion(
                new CouponSearchCondition(null, promotionId.getId())))
                .hasSize(100);
    }

    @Test
    @DisplayName("batch insert using jdbcTemplate")
    void createAllByBulkInsert() {
        PromotionId promotionId = PromotionId.of("promotionTest2");
        service.createAllByBatchInsert(new PromotionCreatedEvent(promotionId, 100,
                new CouponDetails(LocalDateTime.of(2024, 10, 28, 00, 00), DiscountPolicy.TEN_PERCENTAGE)));
        assertThat(repository.findCouponsInPromotion(
                new CouponSearchCondition(null, promotionId.getId())))
                .hasSize(100);
    }

    @Test
    void count(){
        PromotionId promotionTest2 = PromotionId.of("promotion2");
        int count1 = service.count(promotionTest2.getId());
        assertThat(count1).isEqualTo(2);

        PromotionId promotionTest3 = PromotionId.of("promotion3");
        int count2 = service.count(promotionTest3.getId());
        assertThat(count2).isEqualTo(1);
    }

    @Test
    void myCoupons(){
        MemberId memberId1 = MemberId.of("1");
        List<CouponDto> couponDtos1 = service.myCoupons(memberId1.getId());
        assertThat(couponDtos1).hasSize(1);

        MemberId memberId2 = MemberId.of("2");
        List<CouponDto> couponDtos2 = service.myCoupons(memberId2.getId());
        assertThat(couponDtos2).hasSize(0);
    }

    @Test
    void findById(){

        CouponId id = CouponId.of("coupon6");
        CouponDto findCoupon = service.findById(id.getId());
        assertThat(findCoupon.getCouponId()).isEqualTo(id.getId());

        CouponId noId = CouponId.of("couponId1xxxxx");
        assertThrows(CouponException.class, () -> service.findById(noId.getId()));
    }
}