package com.hcs.coupon.infra.repository;

import com.hcs.coupon.domain.Coupon;
import com.hcs.coupon.domain.CouponId;
import com.hcs.coupon.dto.CouponDto;
import com.hcs.coupon.dto.CouponSearchCondition;
import com.hcs.coupon.dto.QCouponDto;
import com.hcs.member.MemberId;
import com.hcs.promotion.domain.PromotionId;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.hibernate.LockOptions;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static com.hcs.coupon.domain.QCoupon.coupon;

@RequiredArgsConstructor
public class CustomCouponRepositoryImpl implements CustomCouponRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<CouponId> findCouponsInPromotion(CouponSearchCondition condition) {
		return queryFactory.select(
						coupon.couponId
				).from(coupon)
				.where(promotionEq(condition.promotionId()),
						notAssignedCoupon()
				)
				.orderBy(new OrderSpecifier<>(Order.ASC, coupon.couponId.id))
				.fetch();


	}

	@Override
	public Optional<Coupon> findCouponInPromotionAndCanIssue(CouponSearchCondition condition) {
		return Optional.ofNullable(
				queryFactory.select(
								coupon
						).from(coupon)
						.where(promotionEq(condition.promotionId()),
								notAssignedCoupon()
						)
						.orderBy(new OrderSpecifier<>(Order.ASC, coupon.couponId.id))
						.limit(1)
						.setLockMode(LockModeType.PESSIMISTIC_WRITE)
						.setHint(
								AvailableSettings.JAKARTA_LOCK_TIMEOUT,
								LockOptions.SKIP_LOCKED
//								LockMode.UPGRADE_SKIPLOCKED.toLockOptions().getTimeOut()
						)
						.fetchFirst());
	}

	@Override
	public List<CouponDto> findMyCoupons(String memberId) {

		return queryFactory.select(
						new QCouponDto(
								coupon.couponId,
								coupon.promotionId,
								coupon.issuedDate,
								coupon.memberId,
								coupon.state,
								coupon.details
						)).from(coupon)
				.where(coupon.memberId.eq(MemberId.of(memberId)))
				.orderBy(new OrderSpecifier<>(Order.DESC, coupon.issuedDate))
				.fetch();
	}

	@Override
	public CouponId findAssignedCoupon(CouponSearchCondition condition) {
		return queryFactory.select(
						coupon.couponId
				).from(coupon)
				.where(promotionEq(condition.promotionId()),
						assignedCoupon(condition.memberId()
						))
				.orderBy(new OrderSpecifier<>(Order.ASC, coupon.couponId.id))
				.fetchFirst();
	}

	private BooleanExpression promotionEq(String promotionId) {
		return StringUtils.hasText(promotionId) ? coupon.promotionId.eq(PromotionId.of(promotionId)) : null;
	}

	private BooleanExpression notAssignedCoupon() {
		return coupon.memberId.isNull();
	}

	private BooleanExpression assignedCoupon(String memberId) {
		return coupon.memberId.eq(MemberId.of(memberId));
	}
}
