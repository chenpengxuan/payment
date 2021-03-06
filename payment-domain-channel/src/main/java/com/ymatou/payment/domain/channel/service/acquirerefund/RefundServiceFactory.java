/*
 * (C) Copyright 2016 Ymatou (http://www.ymatou.com/). All rights reserved.
 */
package com.ymatou.payment.domain.channel.service.acquirerefund;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymatou.payment.domain.channel.service.AcquireRefundService;
import com.ymatou.payment.facade.BizException;
import com.ymatou.payment.facade.ErrorCode;
import com.ymatou.payment.facade.constants.PayTypeEnum;

import javax.annotation.Resource;

/**
 * @author qianmin 2016年6月7日 下午5:57:15
 */
@Component
public class RefundServiceFactory {


    @Autowired
    private AliPayRefundServiceImpl aliPayRefundServiceImpl;

    @Autowired
    private WeixinRefundServiceImpl weixinRefundServiceImpl;

    @Autowired
    private CmbRefundServiceImpl cmbRefundServiceImpl;

    @Resource
    private ApplePayRefundServiceImpl applePayRefundServiceImpl;

    public AcquireRefundService getInstanceByPayType(String payType) {

        return getInstanceByPayType(PayTypeEnum.parse(payType));
    }

    public AcquireRefundService getInstanceByPayType(PayTypeEnum payType) {

        switch (payType) {
            case AliPayPc:
                return aliPayRefundServiceImpl;
            case AliPayApp:
                return aliPayRefundServiceImpl;
            case AliPayWap:
                return aliPayRefundServiceImpl;
            case WeiXinJSAPI:
                return weixinRefundServiceImpl;
            case WeiXinApp:
                return weixinRefundServiceImpl;
            case WeiXinPc:
                return weixinRefundServiceImpl;
            case CmbApp:
                return cmbRefundServiceImpl;
            case ApplePay:
                return applePayRefundServiceImpl;
            default:
                throw new BizException(ErrorCode.INVALID_PAY_TYPE, payType.getCode());
        }
    }
}
