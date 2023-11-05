package com.hcs.coupon.infra.repository;

import com.hcs.coupon.domain.CouponId;
import com.hcs.coupon.dto.CouponSearchCondition;
import com.hcs.member.MemberId;
import com.hcs.promotion.domain.PromotionId;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.hcs.coupon.domain.QCoupon.*;

@RequiredArgsConstructor
public class CustomCouponRepositoryImpl implements CustomCouponRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CouponId> findCouponsInPromotion(CouponSearchCondition condition) {
        return queryFactory.select(
                        coupon.couponId
                ).from(coupon)
                .where(promotionEq(condition.promotionId()),
                        notAssignedCoupon()
                ).fetch();
    }

    @Override
    public CouponId findCouponWithMember(CouponSearchCondition condition) {
        return queryFactory.select(
                        coupon.couponId
                ).from(coupon)
                .where(promotionEq(condition.promotionId()),
                        assignedCoupon(condition.memberId()
                        )).fetchFirst();
    }

    private BooleanExpression promotionEq(String promotionId){
        return StringUtils.hasText(promotionId) ? coupon.promotionId.eq(PromotionId.of(promotionId)):null;
    }

    private BooleanExpression notAssignedCoupon(){
        return coupon.memberId.isNull();
    }
    private BooleanExpression assignedCoupon(String memberId){
        return coupon.memberId.eq(MemberId.of(memberId));
    }
}
