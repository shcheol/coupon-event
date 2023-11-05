package com.hcs.coupon.ui;

import com.hcs.coupon.application.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class CouponController {

	private final CouponService couponService;

	@GetMapping("/coupons/{memberId}")
	public String myCoupons(@PathVariable("memberId") String memberId, Model model){


		//실패 - 중복요청

		// 수량 부족

		//

		// 성공
		return "coupon/couponDetail";
	}

}
