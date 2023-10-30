package com.hcs.promotion.infra.repository;

import com.hcs.promotion.domain.Promotion;
import com.hcs.promotion.dto.PromotionDto;
import com.hcs.promotion.dto.SearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomPromotionRepository {

	Page<PromotionDto> findPromotionsByCondition(SearchCondition condition, Pageable pageable);
}
