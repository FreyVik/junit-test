package com.freyvik.jUnit_test.app.simple_junit.exceptions;

public class InsufficientMoneyException extends RuntimeException {

    public InsufficientMoneyException(String s) {
        super(s);
    }
}
