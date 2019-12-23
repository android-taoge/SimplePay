package com.tang.wechatpay.wechatpay;

import com.tang.simplepay.base.IPayInfo;

public class WeChatPayInfo implements IPayInfo {

    private String partnerId;
    private String prepayId;
    private String packageValue;
    private String nonceStr;
    private String appid;
    private String timeStamp;
    private String sign;


    public String getPartnerId() {
        return partnerId == null ? "" : partnerId;

    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPrepayId() {
        return prepayId == null ? "" : prepayId;

    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getPackageValue() {
        return packageValue == null ? "" : packageValue;

    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public String getNonceStr() {
        return nonceStr == null ? "" : nonceStr;

    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getAppid() {
        return appid == null ? "" : appid;

    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getTimeStamp() {
        return timeStamp == null ? "" : timeStamp;

    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSign() {
        return sign == null ? "" : sign;

    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
