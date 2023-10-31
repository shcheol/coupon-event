package com.hcs.promotion.infra.repository;

import com.hcs.promotion.dto.PromotionDto;
import com.hcs.promotion.dto.SearchCondition;
import com.querydsl.core.Tuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomPromotionRepository {

	Page<PromotionDto> findPromotionsByCondition(SearchCondition condition, Pageable pageable);

	List<Tuple> stocksGroupByPromotion(SearchCondition condition);
}
