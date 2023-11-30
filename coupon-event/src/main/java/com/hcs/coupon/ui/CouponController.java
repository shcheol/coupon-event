package com.hcs.coupon.ui;

import com.hcs.common.exception.CouponException;
import com.hcs.coupon.application.CouponService;
import com.hcs.coupon.domain.CouponId;
import com.hcs.coupon.domain.CouponIssuedEvent;
import com.hcs.coupon.dto.CouponDto;
import com.hcs.member.MemberDto;
import com.hcs.promotion.dto.JoinPromotionRequest;
import com.hcs.promotion.dto.JoinResult;
import com.hcs.web.login.LoginConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class CouponController {

	private final CouponService couponService;

	@GetMapping("/coupons/{couponId}")
	public String details(@SessionAttribute(name = LoginConst.LOGIN_MEMBER, required = false) MemberDto loginMember,
							  @PathVariable("couponId") String couponId, Model model){

		model.addAttribute("coupon", couponService.findById(couponId));
		model.addAttribute("member", loginMember);

		return "my/couponDetail";
	}

	@PatchMapping("/coupons")
	@ResponseBody
	public ResponseEntity<JoinResult> joinPromotion(@RequestBody JoinPromotionRequest request) {
		String promotionId = request.promotionId();
		String memberId = request.memberId();
		log.info("promotionId: {}, memberId: {}", promotionId, memberId);
		if (!StringUtils.hasText(memberId)){
			return ResponseEntity.badRequest().body(new JoinResult("login first"));
		}
		CouponIssuedEvent couponIssuedEvent = new CouponIssuedEvent(memberId, promotionId);
		try {
			couponService.issue(couponIssuedEvent);
		}catch (CouponException e){
			return ResponseEntity.badRequest().body(new JoinResult(e.getMessage()));
		}
		return ResponseEntity.ok(new JoinResult("발급되었습니다."));
	}

	@GetMapping("/my/coupons/{memberId}")
	public String myCoupons(@PathVariable("memberId") String memberId, Model model){

		List<CouponDto> couponDtos = couponService.myCoupons(memberId);
		model.addAttribute("coupons", couponDtos);

		return "my/coupons";
	}

}
