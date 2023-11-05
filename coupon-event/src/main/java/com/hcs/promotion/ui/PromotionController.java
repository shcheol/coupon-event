package com.hcs.promotion.ui;

import com.hcs.common.exception.CouponException;
import com.hcs.member.MemberDto;
import com.hcs.promotion.application.PromotionService;
import com.hcs.promotion.dto.PromotionDto;
import com.hcs.promotion.dto.SearchCondition;
import com.hcs.web.login.LoginConst;
import com.hcs.web.login.LoginForm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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
	public String detail(@SessionAttribute(name = LoginConst.LOGIN_MEMBER, required = false) MemberDto loginMember,
						 @PathVariable("promotionId") String promotionId, Model model){
		log.info("{}",promotionId);

		PromotionDto promotionDto = promotionService.findByPromotionId(promotionId);
		model.addAttribute("promotion", promotionDto);
		if(loginMember == null){
			model.addAttribute("member", new MemberDto(""));
		}else {
			model.addAttribute("member", loginMember);
		}
		return "promotion/promotionDetail";
	}

	@PostMapping("/promotions/{promotionId}")
	public String joinPromotion(@SessionAttribute(name = LoginConst.LOGIN_MEMBER, required = false) MemberDto loginMember,
								@PathVariable("promotionId") String promotionId, Model model,HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("{}",promotionId);
		log.info(String.valueOf(loginMember));
		if (loginMember == null){
			response.sendRedirect("/login?redirectURL=" + request.getRequestURI());
			model.addAttribute("loginForm", new LoginForm(""));
			return "loginForm";
		}

		promotionService.joinPromotion(loginMember.id(), promotionId);

		return "promotion/promotionResult";
	}

	@ExceptionHandler(CouponException.class)
	public String handleNoPromotion() {
		return "promotion/noPromotion";
	}
}
