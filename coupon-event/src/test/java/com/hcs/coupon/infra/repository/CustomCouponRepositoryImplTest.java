package com.hcs.coupon.infra.repository;

import com.hcs.coupon.domain.CouponId;
import com.hcs.coupon.dto.CouponSearchCondition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql("classpath:test-init.sql")
class CustomCouponRepositoryImplTest {

    @Autowired
    CouponRepository repository;

    @Test
    void conditionNullTest(){
        List<CouponId> couponsInPromotion = repository.findCouponsInPromotion(new CouponSearchCondition(null, null));

        assertThat(couponsInPromotion).hasSize(12);

    }

}