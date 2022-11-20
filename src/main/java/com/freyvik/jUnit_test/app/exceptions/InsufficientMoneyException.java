package com.freyvik.jUnit_test.app.exceptions;

public class InsufficientMoneyException extends RuntimeException {

    public InsufficientMoneyException(String s) {
        super(s);
    }
}
