package com.hcs.promotion.domain;

import com.hcs.common.exception.CouponError;
import com.hcs.common.exception.CouponException;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Getter
public class PromotionPeriod {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public PromotionPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        validateDates(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validateDates(LocalDateTime startDate, LocalDateTime endDate){
        if (startDate==null || endDate == null ||startDate.isEqual(endDate) || startDate.isAfter(endDate)){
            throw new CouponException(CouponError.INVALID_PERIOD_INPUT);
        }
    }
}
