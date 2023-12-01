package com.hcs.coupon.infra.repository;

import com.hcs.coupon.domain.Coupon;
import com.hcs.coupon.domain.CouponId;
import com.hcs.promotion.domain.PromotionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, CouponId>, CustomCouponRepository, JdbcCouponRepository {

	long countCouponsByPromotionIdAndMemberIdIsNull(PromotionId promotionId);

}
