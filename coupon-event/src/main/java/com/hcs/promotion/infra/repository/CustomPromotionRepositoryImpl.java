package com.hcs.promotion.infra.repository;

import com.hcs.promotion.dto.PromotionDto;
import com.hcs.promotion.dto.QPromotionDto;
import com.hcs.promotion.dto.SearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.time.LocalDateTime;
import java.util.List;

import static com.hcs.promotion.domain.QPromotion.*;

@RequiredArgsConstructor
public class CustomPromotionRepositoryImpl implements CustomPromotionRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public Page<PromotionDto> findPromotionsByCondition(SearchCondition condition, Pageable pageable) {

		List<PromotionDto> fetch = queryFactory.select(new QPromotionDto(
						promotion.promotionId,
						promotion.title,
						promotion.context,
						promotion.quantity,
						promotion.discountPolicy,
						promotion.period
				)).from(promotion)
				.where(
						proceeding(condition.getNow(), condition.isProceeding())
				)
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();

		JPAQuery<Long> count = queryFactory.select(promotion.count())
				.from(promotion)
				.where(
						proceeding(condition.getNow(), condition.isProceeding())
				);
		return PageableExecutionUtils.getPage(fetch, pageable, count::fetchOne);
	}

	private BooleanExpression proceeding(LocalDateTime now, boolean proceeding) {
		return now!=null? proceeding?promotion.period.endDate.after(now).and(promotion.period.startDate.before(now)):null:null;
	}
}
