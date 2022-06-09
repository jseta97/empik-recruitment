package com.example.empikrecruitment.exception;

public class UserException extends RuntimeException{
    public UserException(String msg) {
        super(msg);
    }

    public UserException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
