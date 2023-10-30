package com.hcs.promotion.application;

import com.hcs.common.exception.CouponError;
import com.hcs.common.exception.CouponException;
import com.hcs.coupon.domain.CouponDetails;
import com.hcs.promotion.domain.Promotion;
import com.hcs.promotion.domain.PromotionId;
import com.hcs.promotion.infra.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PromotionService {

    private final PromotionRepository repository;

    @Transactional
    public Promotion create(CreatePromotionRequest request){
        return repository.save(
                Promotion.create(request.getTitle(), request.getContext(), request.getQuality(),
                request.getDiscountPolicy(), request.getPeriod(), new CouponDetails(LocalDateTime.now(), request.getDiscountPolicy())));
    }

    @Transactional
    public Promotion findByPromotionId(PromotionId promotionId){
        return repository.findById(promotionId).orElseThrow(() -> new CouponException(CouponError.NOT_FOUND));
    }

}
