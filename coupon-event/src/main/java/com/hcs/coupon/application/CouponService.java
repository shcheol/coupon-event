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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
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

    public int count(String promotionId){
        CouponSearchCondition condition = new CouponSearchCondition(null, promotionId);

        List<CouponId> coupons = couponRepository.findCouponsInPromotion(
                condition);

        return coupons.size();
    }
    public CouponId issue(CouponIssuedEvent event){
        CouponSearchCondition condition = new CouponSearchCondition(event.getMemberId(), event.getPromotionId());
        CouponId couponWithMember = couponRepository.findAssignedCoupon(condition);
        if (couponWithMember != null){
            throw new CouponException(CouponError.DUPLICATE_PARTICIPATION);
        }

        List<CouponId> coupons = couponRepository.findCouponsInPromotion(
                condition);

        if (coupons.isEmpty()){
            throw new CouponException(CouponError.EMPTY_STOCK);
        }
        CouponId couponId = coupons.get(0);
        Optional<Coupon> coupon = couponRepository.findById(couponId);
        coupon.get().issuedCoupon(MemberId.of(event.getMemberId()));
        return coupon.get().getCouponId();
    }

	public List<CouponDto> myCoupons(String memberId){
		return couponRepository.findMyCoupons(memberId);
	}

	public CouponDto findById(String couponId){
		return CouponDto.convert(
				couponRepository.findById(CouponId.of(couponId)).orElseThrow(() -> new CouponException(CouponError.NOT_FOUND)));
	}
}
