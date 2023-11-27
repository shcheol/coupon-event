package com.hcs.coupon.ui;

import com.hcs.coupon.application.CouponService;
import com.hcs.coupon.domain.Coupon;
import com.hcs.coupon.domain.CouponDetails;
import com.hcs.coupon.domain.DiscountPolicy;
import com.hcs.coupon.dto.CouponDto;
import com.hcs.member.MemberDto;
import com.hcs.member.MemberId;
import com.hcs.promotion.domain.PromotionId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static com.hcs.web.login.LoginConst.LOGIN_MEMBER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(CouponController.class)
@ExtendWith(MockitoExtension.class)
class CouponControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CouponService couponService;

    MockHttpSession session;

    @BeforeEach
    void setUp(){
        session = new MockHttpSession();
        session.setAttribute(LOGIN_MEMBER, "memberId");
    }

    @AfterEach
    void clean(){
        session.clearAttributes();
    }

    @Test
    void couponDetails() throws Exception {

        Coupon coupon = Coupon.create(
                PromotionId.newId(), new CouponDetails(LocalDateTime.now(), DiscountPolicy.TEN_PERCENTAGE)
        );
        coupon.issuedCoupon(MemberId.of("memberId"));
        CouponDto couponDto = CouponDto.convert(coupon);

        when(couponService.findById(any()))
                .thenReturn(couponDto);

        mockMvc.perform(get("/coupons/"+couponDto.getCouponId())
                .session(session))
                .andExpect(model().attribute("coupon",couponDto))
                .andExpect(model().attribute("member", new MemberDto("memberId")))
                .andExpect(view().name("my/couponDetail"));

    }

    @Test
    void myCoupons() throws Exception {

        String memberId = "memberId";

        Coupon coupon1 = Coupon.create(
                PromotionId.newId(), new CouponDetails(LocalDateTime.now(), DiscountPolicy.TEN_PERCENTAGE));
        coupon1.issuedCoupon(MemberId.of(memberId));
        Coupon coupon2 = Coupon.create(
                PromotionId.newId(), new CouponDetails(LocalDateTime.now(), DiscountPolicy.TEN_PERCENTAGE));
        coupon2.issuedCoupon(MemberId.of(memberId));

        CouponDto couponDto1 = CouponDto.convert(coupon1);
        CouponDto couponDto2 = CouponDto.convert(coupon2);

        List<CouponDto> couponDtos = List.of(couponDto1, couponDto2);
        when(couponService.myCoupons(memberId))
                .thenReturn(couponDtos);

        mockMvc.perform(get("/my/coupons/"+memberId)
                        .session(session))
                .andExpect(model().attribute("coupons",couponDtos))
                .andExpect(view().name("my/coupons"));

    }
}