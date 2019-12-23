package com.tang.simplepay.base;

import android.app.Activity;

public interface IPayStrategy<T extends IPayInfo> {

    void pay(Activity activity, T payInfo, IPayCallBack callBack);
}
