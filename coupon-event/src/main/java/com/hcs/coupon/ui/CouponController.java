package com.hcs.coupon.ui;

import com.hcs.coupon.application.CouponService;
import com.hcs.coupon.dto.CouponDto;
import com.hcs.member.MemberDto;
import com.hcs.web.login.LoginConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@GetMapping("/my/coupons/{memberId}")
	public String myCoupons(@PathVariable("memberId") String memberId, Model model){

		List<CouponDto> couponDtos = couponService.myCoupons(memberId);
		model.addAttribute("coupons", couponDtos);

		return "my/coupons";
	}

}
