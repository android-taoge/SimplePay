package com.tang.simplepay.base;

public class PayException extends Exception {

    private int code;

    public PayException(int code) {
        this.code = code;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
