package com.tang.simple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tang.alipay.AliPay;
import com.tang.alipay.AlipayInfo;
import com.tang.simplepay.SimplePay;
import com.tang.simplepay.base.IPayCallBack;
import com.tang.wechatpay.wechatpay.WeChatPay;
import com.tang.wechatpay.wechatpay.WeChatPayInfo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAlipay, btnWechatPay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        btnAlipay = findViewById(R.id.btn_alipay);
        btnWechatPay = findViewById(R.id.btn_weChatPay);

        btnAlipay.setOnClickListener(this);
        btnWechatPay.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_alipay:
                aliPay();
                break;


            case R.id.btn_weChatPay:
                weChatPay();
                break;
        }
    }

    private void weChatPay() {
        WeChatPay weChatPay = WeChatPay.getInstance();
        WeChatPayInfo weChatPayInfo = new WeChatPayInfo();
        weChatPayInfo.setAppid("");
        weChatPayInfo.setPartnerId("");
        weChatPayInfo.setPrepayId("");
        weChatPayInfo.setNonceStr("");
        weChatPayInfo.setPackageValue("");
        weChatPayInfo.setTimeStamp("");
        weChatPayInfo.setSign("");
        SimplePay.pay(this, weChatPay, weChatPayInfo, new IPayCallBack() {
            @Override
            public void success() {

            }

            @Override
            public void failed(int code, String msg) {

            }

            @Override
            public void cancel() {

            }
        });
    }

    private void aliPay() {
        AliPay aliPay = new AliPay();
        AlipayInfo alipayInfo = new AlipayInfo();
        alipayInfo.setPayOrderInfo("");
        SimplePay.pay(this, aliPay, alipayInfo, new IPayCallBack() {
            @Override
            public void success() {

            }

            @Override
            public void failed(int code, String msg) {

            }

            @Override
            public void cancel() {

            }
        });

    }
}
