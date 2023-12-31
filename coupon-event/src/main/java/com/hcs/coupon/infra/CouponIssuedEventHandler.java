package com.hcs.coupon.infra;

import com.hcs.coupon.application.CouponService;
import com.hcs.coupon.domain.CouponIssuedEvent;
import com.hcs.promotion.domain.PromotionCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CouponIssuedEventHandler {

    private final CouponService couponService;

    @Async
//    @TransactionalEventListener(
//            classes = CouponIssuedEvent.class,
//            phase = TransactionPhase.AFTER_COMMIT
//    )
    @EventListener(CouponIssuedEvent.class)
    public void handle(CouponIssuedEvent event) {
        log.info("handle PromotionCreatedEvent");
        couponService.issue(event);
    }
}
