package com.hcs.coupon.application;

import com.hcs.common.exception.CouponError;
import com.hcs.common.exception.CouponException;
import com.hcs.coupon.domain.Coupon;
import com.hcs.coupon.domain.CouponId;
import com.hcs.coupon.domain.CouponIssuedEvent;
import com.hcs.coupon.dto.CouponDto;
import com.hcs.coupon.dto.CouponSearchCondition;
import com.hcs.coupon.infra.repository.CouponRepository;
import com.hcs.member.MemberId;
import com.hcs.promotion.domain.PromotionCreatedEvent;
import com.hcs.promotion.domain.PromotionId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class CouponService {

    private final CouponRepository couponRepository;

    public void createAll(PromotionCreatedEvent event){
        couponRepository.saveAll(
                Coupon.createAll(event.getPromotionId(), event.getQuantity(), event.getDetails()));
    }

    public void createAllByBatchInsert(PromotionCreatedEvent event){
        couponRepository.batchInsert(
                Coupon.createAll(event.getPromotionId(), event.getQuantity(), event.getDetails()));
    }

    public long count(String promotionId){
		return couponRepository.countCouponsByPromotionIdAndMemberIdIsNull(PromotionId.of(promotionId));
    }

	@Transactional
    public CouponId issue(CouponIssuedEvent event){

        CouponSearchCondition condition = new CouponSearchCondition(event.getMemberId(), event.getPromotionId());
		log.info("coupon issue event {}", event);
		checkDuplicateParticipation(condition);

		Coupon coupon = couponRepository.findCouponInPromotionAndCanIssue(condition)
				.orElseThrow(() -> {
					log.info("member {} {}", condition.memberId(), CouponError.EMPTY_STOCK.getMessage());
					throw new CouponException(CouponError.EMPTY_STOCK);
				});

        coupon.issuedCoupon(MemberId.of(event.getMemberId()));
		log.info("member {} issue coupon", condition.memberId());

        return coupon.getCouponId();
    }

	private void checkDuplicateParticipation(CouponSearchCondition condition) {
		CouponId couponWithMember = couponRepository.findAssignedCoupon(condition);
		if (couponWithMember != null){
			log.info("member {} {}", condition.memberId(), CouponError.DUPLICATE_PARTICIPATION.getMessage());
			throw new CouponException(CouponError.DUPLICATE_PARTICIPATION);
		}
	}

	public List<CouponDto> myCoupons(String memberId){
		return couponRepository.findMyCoupons(memberId);
	}

	public CouponDto findById(String couponId){
		return CouponDto.convert(
				couponRepository.findById(CouponId.of(couponId)).orElseThrow(() -> new CouponException(CouponError.NOT_FOUND)));
	}
}
