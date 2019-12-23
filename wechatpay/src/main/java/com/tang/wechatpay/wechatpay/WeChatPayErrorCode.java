package com.tang.wechatpay.wechatpay;

import com.tencent.mm.opensdk.modelbase.BaseResp;

import java.util.HashMap;

public class WeChatPayErrorCode implements BaseResp.ErrCode {

    private static HashMap<Integer, String> errorMap = new HashMap<>();

    public static final int CODE_UNSUPPORT = 1001;
    public static final int CODE_ILLEGAL_ARGUMENT = 1000;

    public static final String MSG_UNSUPPORT = "不支持微信支付！";
    public static final String MSG_ILLEGAL_ARGUMENT = "参数不合法";

    static {
        errorMap.put(CODE_UNSUPPORT, MSG_UNSUPPORT);
        errorMap.put(CODE_ILLEGAL_ARGUMENT, MSG_ILLEGAL_ARGUMENT);
    }

    public static String getMsgByCode(int code) {
        return errorMap.get(code);
    }

}
