package com.hcs.promotion.ui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hcs.coupon.application.CouponService;
import com.hcs.coupon.domain.CouponDetails;
import com.hcs.coupon.domain.DiscountPolicy;
import com.hcs.promotion.application.PromotionService;
import com.hcs.promotion.domain.PromotionId;
import com.hcs.promotion.domain.PromotionPeriod;
import com.hcs.promotion.dto.CreatePromotionRequest;
import com.hcs.promotion.dto.JoinPromotionRequest;
import com.hcs.promotion.dto.PromotionDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PromotionController.class)
@ExtendWith(MockitoExtension.class)
class PromotionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PromotionService promotionService;

    @MockBean
    CouponService couponService;

    static ObjectMapper om;

    @BeforeAll
    static void init() {
        om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        om.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Test
    @DisplayName("promotion 참여 - 로그인 안한 멤버")
    void joinPromotionsWithoutLogin() throws Exception {
        JoinPromotionRequest request = new JoinPromotionRequest(null, "promotion1");
        mockMvc.perform(
                        patch("/promotions")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(om.writeValueAsString(request))
                ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("result").value("login first"))
                .andDo(print());
    }

    @Test
    @DisplayName("promotion 참여 - 로그인 멤버")
    void joinPromotionsWithLogin() throws Exception {
        JoinPromotionRequest request = new JoinPromotionRequest("1", "promotion1");
        mockMvc.perform(
                        patch("/promotions")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(om.writeValueAsString(request))
                ).andExpect(status().isOk())
                .andExpect(jsonPath("result").value("request..."))
                .andDo(print());
    }

    @Test
    @DisplayName("promotion 생성")
    void create() throws Exception {

        CreatePromotionRequest createPromotionRequest =
                new CreatePromotionRequest("title", "context", 500, DiscountPolicy.TEN_PERCENTAGE,
                        new PromotionPeriod(
                                LocalDateTime.of(2023, 10, 28, 00, 00),
                                LocalDateTime.of(2024, 10, 28, 00, 00)),
                        new CouponDetails(
                                LocalDateTime.of(2024, 10, 28, 00, 00),
                                DiscountPolicy.TEN_PERCENTAGE));

        when(promotionService.create(any()))
                .thenReturn(
                        new PromotionDto(
                                PromotionId.of("promotionTestId"),
                                "title",
                                "context",
                                500,
                                DiscountPolicy.TEN_PERCENTAGE,
                                new PromotionPeriod(
                                        LocalDateTime.of(2023, 10, 28, 00, 00),
                                        LocalDateTime.of(2024, 10, 28, 00, 00))
                        )
                );


        mockMvc.perform(
                        post("/promotions")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(om.writeValueAsString(createPromotionRequest))
                ).andExpect(status().isCreated())
                .andExpect(jsonPath("promotionId").value("promotionTestId"))
                .andExpect(jsonPath("title").value("title"))
                .andExpect(jsonPath("context").value("context"))
                .andExpect(jsonPath("quantity").value("500"))
                .andExpect(jsonPath("discountPolicy").value("TEN_PERCENTAGE"))
                .andExpect(jsonPath("period.startDate").value("2023-10-28T00:00:00"))
                .andExpect(jsonPath("period.endDate").value("2024-10-28T00:00:00"));

        verify(promotionService).create(createPromotionRequest);

    }
}