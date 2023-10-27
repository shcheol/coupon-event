package com.hcs.common.exception;

public class CouponException extends RuntimeException{
    private final CouponError error;

    public CouponException(CouponError error) {
        super(error.getMessage());
        this.error = error;
    }

    public CouponException(String message, CouponError error) {
        super(message);
        this.error = error;
    }

    public CouponException(String message, Throwable cause, CouponError error) {
        super(message, cause);
        this.error = error;
    }

    public CouponException(Throwable cause, CouponError error) {
        super(cause);
        this.error = error;
    }

    public CouponException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, CouponError error) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.error = error;
    }

}
