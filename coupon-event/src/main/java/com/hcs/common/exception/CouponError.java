package com.hcs.common.exception;

import lombok.Getter;

@Getter
public enum CouponError {

    INVALID_PERIOD("유효하지 않은 기간입니다."),
    ;

    private String message;

    CouponError(String message) {
        this.message = message;
    }
}
