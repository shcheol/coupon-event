package com.hcs.promotion.application;

import com.hcs.common.exception.CouponError;
import com.hcs.common.exception.CouponException;
import com.hcs.coupon.domain.CouponDetails;
import com.hcs.promotion.domain.Promotion;
import com.hcs.promotion.domain.PromotionId;
import com.hcs.promotion.dto.PromotionDto;
import com.hcs.promotion.dto.SearchCondition;
import com.hcs.promotion.infra.repository.PromotionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public PromotionDto findByPromotionId(String promotionId){
		Promotion promotion = repository.findById(PromotionId.of(promotionId)).orElseThrow(() -> new CouponException(CouponError.NOT_FOUND));
		return PromotionDto.convert(promotion);
	}

	@Transactional
	public Page<PromotionDto> findByPromotions(SearchCondition condition, Pageable pageable){
		return repository.findPromotionsByCondition(condition, pageable);
	}

}
