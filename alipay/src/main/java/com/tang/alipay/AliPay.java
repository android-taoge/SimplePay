package com.tang.alipay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import com.alipay.sdk.app.PayTask;
import com.tang.simplepay.base.IPayCallBack;
import com.tang.simplepay.base.IPayStrategy;

import java.util.Map;

public class AliPay implements IPayStrategy<AlipayInfo> {

    private static final int PAY_FLAG = 6406;
    private Activity mActivity;
    private AlipayInfo mPayInfo;
    private static IPayCallBack mCallback;


    @Override
    public void pay(Activity activity, final AlipayInfo payInfo, IPayCallBack callBack) {
        this.mActivity = activity;
        this.mPayInfo = payInfo;
        if (callBack != null) {
            mCallback = callBack;
        }

        Thread payThread = new Thread(new Runnable() {
            @Override
            public void run() {
                PayTask payTask = new PayTask(mActivity);
                Map<String, String> result = payTask.payV2(payInfo.getPayOrderInfo(), true);
                Message message = new Message();
                message.what = PAY_FLAG;
                message.obj = result;
                mHandler.sendMessage(message);
            }
        });

        payThread.start();
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            switch (msg.what) {
                case PAY_FLAG:

                    AlipayResult alipayResult = new AlipayResult((Map<String, String>) msg.obj);
                    String resultInfo = alipayResult.getResult();
                    String resultStatus = alipayResult.getResultStatus();
                    if (TextUtils.equals(resultStatus, AliPayResultCode.CODE_SUCCESS)) {

                        mCallback.success();

                    } else if (TextUtils.equals(resultStatus, AliPayResultCode.CODE_FAIL)) {

                        mCallback.failed(Integer.valueOf(resultStatus), resultInfo);
                    } else {

                        mCallback.cancel();
                    }

                    break;

            }

        }
    };
}
