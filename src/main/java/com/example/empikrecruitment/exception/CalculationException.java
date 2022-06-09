package com.example.empikrecruitment.exception;

public class CalculationException extends RuntimeException {
    public CalculationException(String msg) {
        super(msg);
    }

    public CalculationException(String msg, Throwable ex) {
        super(msg, ex);
    }
}