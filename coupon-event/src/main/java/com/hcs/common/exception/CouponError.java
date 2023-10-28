package com.hcs.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CouponError {

    INVALID_PERIOD_INPUT("유효하지 않은 기간입니다.", HttpStatus.BAD_REQUEST),
    ;

    private String message;

    private HttpStatus status;

    CouponError(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
