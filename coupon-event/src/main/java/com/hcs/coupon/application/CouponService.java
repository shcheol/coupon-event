package com.hcs.coupon.application;

import com.hcs.coupon.domain.Coupon;
import com.hcs.coupon.repository.CouponRepository;
import com.hcs.promotion.domain.PromotionCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    @Transactional
    public void createAll(PromotionCreatedEvent event){
        couponRepository.saveAll(
                Coupon.createAll(event.getPromotionId(), event.getQuantity(), event.getDetails()));
    }
}
