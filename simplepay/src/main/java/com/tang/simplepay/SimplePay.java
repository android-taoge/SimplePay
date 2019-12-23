package com.tang.simplepay;

import android.app.Activity;

import com.tang.simplepay.base.IPayCallBack;
import com.tang.simplepay.base.IPayInfo;
import com.tang.simplepay.base.IPayStrategy;

public class SimplePay {

    public static <T extends IPayInfo> void pay(Activity activity, IPayStrategy<T> payWay, T payInfo, IPayCallBack callBack) {
        payWay.pay(activity, payInfo, callBack);
    }
}
