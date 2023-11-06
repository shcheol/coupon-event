package com.hcs.promotion.application;

import com.hcs.common.event.Events;
import com.hcs.common.exception.CouponError;
import com.hcs.common.exception.CouponException;
import com.hcs.coupon.domain.CouponDetails;
import com.hcs.coupon.domain.CouponIssuedEvent;
import com.hcs.promotion.domain.Promotion;
import com.hcs.promotion.domain.PromotionId;
import com.hcs.promotion.dto.CreatePromotionRequest;
import com.hcs.promotion.dto.PromotionDto;
import com.hcs.promotion.dto.PromotionSearchCondition;
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
	public PromotionDto create(CreatePromotionRequest request) {
		return PromotionDto.convert(
				repository.save(
						Promotion.create(request.title(),
								request.context(),
								request.quantity(),
								request.discountPolicy(),
								request.period(),
								new CouponDetails(LocalDateTime.now(), request.discountPolicy()))));
	}

	@Transactional
	public PromotionDto findByPromotionId(String promotionId) {
		Promotion promotion = repository.findById(PromotionId.of(promotionId)).orElseThrow(() -> new CouponException(CouponError.NOT_FOUND));
		return PromotionDto.convert(promotion);
	}

	@Transactional
	public Page<PromotionDto> findByPromotions(PromotionSearchCondition condition, Pageable pageable) {
		return repository.findPromotionsByCondition(condition, pageable);
	}

	public void joinPromotion(String memberId, String promotionId) {

		Events.raise(new CouponIssuedEvent(memberId, promotionId));
	}

}
