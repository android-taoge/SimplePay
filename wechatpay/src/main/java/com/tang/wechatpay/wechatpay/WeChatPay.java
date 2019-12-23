package com.tang.wechatpay.wechatpay;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.tang.simplepay.base.IPayCallBack;
import com.tang.simplepay.base.IPayStrategy;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WeChatPay implements IPayStrategy<WeChatPayInfo> {

    private static volatile WeChatPay mWeChatPay;
    private Activity mActivity;
    private WeChatPayInfo mPayInfo;
    private static IPayCallBack mCallBack;
    private IWXAPI mIwxapi;
    private boolean initialize;

    private WeChatPay(){

    }

    public void initWxApi(Context context, String appid) {
        mIwxapi = WXAPIFactory.createWXAPI(context.getApplicationContext(), appid);
        mIwxapi.registerApp(appid);
        initialize = true;
    }


    public IWXAPI getIwxapi() {
        return mIwxapi;
    }

    public static WeChatPay getInstance() {
        if (mWeChatPay == null) {
            synchronized (WeChatPay.class) {
                if (mWeChatPay == null) {
                    mWeChatPay = new WeChatPay();
                }
            }
        }
        return mWeChatPay;
    }

    public boolean checkSupport() {
        return mIwxapi.isWXAppInstalled() && mIwxapi.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
    }

    @Override
    public void pay(Activity activity, WeChatPayInfo payInfo, IPayCallBack callBack) {

        this.mActivity = activity;
        this.mPayInfo = payInfo;
        mCallBack = callBack;

        if (payInfo == null || TextUtils.isEmpty(payInfo.getPartnerId()) ||
                TextUtils.isEmpty(payInfo.getPrepayId()) ||
                TextUtils.isEmpty(payInfo.getPackageValue()) ||
                TextUtils.isEmpty(payInfo.getNonceStr()) ||
                TextUtils.isEmpty(payInfo.getTimeStamp()) ||
                TextUtils.isEmpty(payInfo.getSign()) ||
                TextUtils.isEmpty(payInfo.getAppid())) {

            if (callBack != null) {
                callBack.failed(WeChatPayErrorCode.CODE_ILLEGAL_ARGUMENT, WeChatPayErrorCode.MSG_ILLEGAL_ARGUMENT);
                return;
            }
        }

        if (!initialize) {
            initWxApi(mActivity, mPayInfo.getAppid());
        }

        if (!checkSupport()) {
            callBack.failed(WeChatPayErrorCode.CODE_UNSUPPORT, WeChatPayErrorCode.MSG_UNSUPPORT);
        }

        PayReq req = new PayReq();
        req.appId = mPayInfo.getAppid();
        req.partnerId = mPayInfo.getPartnerId();
        req.prepayId = mPayInfo.getPrepayId();
        req.packageValue = mPayInfo.getPackageValue();
        req.nonceStr = mPayInfo.getNonceStr();
        req.timeStamp = mPayInfo.getTimeStamp();
        req.sign = mPayInfo.getSign();

        mIwxapi.sendReq(req);
    }


    public void onResp(int errorCode, String errorMsg) {
        if (mCallBack == null) {
            return;
        }

        if (errorCode == WeChatPayErrorCode.ERR_OK) {
            mCallBack.success();
        } else if (errorCode == WeChatPayErrorCode.ERR_COMM) {
            mCallBack.failed(WeChatPayErrorCode.ERR_COMM, errorMsg);
        } else if (errorCode == WeChatPayErrorCode.ERR_USER_CANCEL) {
            mCallBack.cancel();
        }

    }


}
