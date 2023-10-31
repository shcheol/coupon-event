package com.hcs.promotion.ui;

import com.hcs.promotion.application.PromotionService;
import com.hcs.promotion.dto.PromotionDto;
import com.hcs.promotion.dto.SearchCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PromotionController {

	private final PromotionService promotionService;

	@GetMapping("/promotions")
	@ResponseBody
	public Page<PromotionDto> promotions(SearchCondition condition, Pageable pageable){
		log.info(condition.toString());
		return promotionService.findByPromotions(condition, pageable);
	}


}
