package com.tang.simplepay.base;

public interface IPayCallBack {

    void success();

    void failed(int code, String msg);

    void cancel();
}
