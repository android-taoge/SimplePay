package com.tang.alipay;

import com.tang.simplepay.base.IPayInfo;

public class AlipayInfo implements IPayInfo {


    private String payOrderInfo;

    public String getPayOrderInfo() {
        return payOrderInfo == null ? "" : payOrderInfo;

    }

    public void setPayOrderInfo(String payOrderInfo) {
        this.payOrderInfo = payOrderInfo;
    }
}
