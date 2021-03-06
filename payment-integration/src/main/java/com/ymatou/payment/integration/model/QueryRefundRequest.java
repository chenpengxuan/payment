/*
 * (C) Copyright 2016 Ymatou (http://www.ymatou.com/). All rights reserved.
 */
package com.ymatou.payment.integration.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * 微信支付退款查询请求model
 * 
 * @author qianmin 2016年5月9日 上午10:42:18
 *
 */
public class QueryRefundRequest {
    /**
     * 公众账号ID(32)
     */
    private String appid;
    /**
     * 商户号(32)
     */
    private String mch_id;
    /**
     * 设备号(32)
     */
    private String device_info;
    /**
     * 随机字符串(32)
     */
    private String nonce_str;
    /**
     * 签名(32)
     */
    private String sign;
    /**
     * 微信订单号(32)
     */
    private String transaction_id;
    /**
     * 商户订单号(32)
     */
    private String out_trade_no;
    /**
     * 商户退款单号(32)
     */
    private String out_refund_no;
    /**
     * 微信退款单号(28)
     */
    private String refund_id;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getRefund_id() {
        return refund_id;
    }

    public void setRefund_id(String refund_id) {
        this.refund_id = refund_id;
    }

    public HashMap<String, String> mapForSign() {
        HashMap<String, String> signMap = new HashMap<>();
        signMap.put("appid", this.getAppid());
        signMap.put("mch_id", this.getMch_id());
        signMap.put("nonce_str", this.getNonce_str());
        signMap.put("out_refund_no", this.getOut_refund_no());

        return signMap;
    }

    public String getRequestData() {
        HashMap<String, String> map = mapForSign();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        sb.append("sign").append("=").append(this.getSign()).append("&");

        return StringUtils.removeEnd(sb.toString(), "&");
    }
}
