package com.hcs.promotion.ui;

import com.hcs.common.exception.CouponException;
import com.hcs.promotion.application.PromotionService;
import com.hcs.promotion.domain.PromotionId;
import com.hcs.promotion.dto.PromotionDto;
import com.hcs.promotion.dto.SearchCondition;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PromotionController {

	private final PromotionService promotionService;

	@GetMapping("/promotions")
	public String promotions(SearchCondition condition, Pageable pageable, Model model){
		log.info(condition.toString());
		Page<PromotionDto> promotions = promotionService.findByPromotions(condition, pageable);
		model.addAttribute("promotions", promotions);
		return "promotion/promotionList";
	}

	@GetMapping("/promotions/{promotionId}")
	public String detail(@PathVariable("promotionId") String promotionId, Model model){
		log.info("{}",promotionId);

		PromotionDto promotionDto = promotionService.findByPromotionId(promotionId);
		model.addAttribute("promotion", promotionDto);
		return "promotion/promotionDetail";
	}

	@ExceptionHandler(CouponException.class)
	public String handleNoPromotion() {
		return "promotion/noPromotion";
	}
}
