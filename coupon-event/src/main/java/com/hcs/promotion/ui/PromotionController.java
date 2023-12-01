package com.hcs.promotion.ui;

import com.hcs.common.exception.CouponException;
import com.hcs.coupon.application.CouponService;
import com.hcs.member.MemberDto;
import com.hcs.promotion.application.PromotionService;
import com.hcs.promotion.dto.*;
import com.hcs.web.login.LoginConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class PromotionController {

	private final PromotionService promotionService;

	private final CouponService couponService;

	@GetMapping("/promotions")
	public String promotions(PromotionSearchCondition condition, Pageable pageable, Model model){
		Page<PromotionDto> promotions = promotionService.findByPromotions(condition, pageable);
		model.addAttribute("promotions", promotions);
		return "promotion/promotionList";
	}

	@GetMapping("/promotions/{promotionId}")
	public String detail(@SessionAttribute(name = LoginConst.LOGIN_MEMBER, required = false) MemberDto loginMember,
						 @PathVariable("promotionId") String promotionId, Model model){
		log.info("{}",promotionId);

		PromotionDto promotionDto = promotionService.findByPromotionId(promotionId);
		model.addAttribute("promotion", promotionDto);
		long count = couponService.count(promotionId);
		model.addAttribute("stock",count);
		if(loginMember == null){
			model.addAttribute("member", new MemberDto(""));
		}else {
			model.addAttribute("member", loginMember);
		}
		return "promotion/promotionDetail";
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/promotions")
	@ResponseBody
	public PromotionDto create(@RequestBody CreatePromotionRequest request){
		log.info("request {}", request);
		return promotionService.create(request);
	}

	@PatchMapping(value = "/promotions", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<JoinResult> joinPromotion(@RequestBody JoinPromotionRequest request) {
		String promotionId = request.promotionId();
		String memberId = request.memberId();
		log.info("promotionId: {}, memberId: {}", promotionId, memberId);
		if (!StringUtils.hasText(memberId)){
			return ResponseEntity.badRequest().body(new JoinResult("login first"));
		}

		promotionService.joinPromotion(memberId, promotionId);

		return ResponseEntity.ok(new JoinResult("request..."));
	}

	@ExceptionHandler(CouponException.class)
	public String handleNoPromotion() {
		return "promotion/noPromotion";
	}
}
