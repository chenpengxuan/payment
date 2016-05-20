/**
 * (C) Copyright 2016 Ymatou (http://www.ymatou.com/).
 *
 * All rights reserved.
 */
package com.ymatou.payment.domain.channel.service.acquireorder;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.esotericsoftware.minlog.Log;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ymatou.payment.domain.channel.InstitutionConfig;
import com.ymatou.payment.domain.channel.InstitutionConfigManager;
import com.ymatou.payment.domain.channel.model.AcquireOrderPackageResp;
import com.ymatou.payment.domain.channel.model.AcquireOrderResultType;
import com.ymatou.payment.domain.channel.service.AcquireOrderService;
import com.ymatou.payment.domain.channel.service.SignatureService;
import com.ymatou.payment.domain.pay.model.Payment;
import com.ymatou.payment.facade.BizException;
import com.ymatou.payment.facade.ErrorCode;
import com.ymatou.payment.facade.model.WeixinJSAPIOrderRequest;
import com.ymatou.payment.infrastructure.util.StringUtil;
import com.ymatou.payment.integration.IntegrationConfig;
import com.ymatou.payment.integration.model.UnifiedOrderRequest;
import com.ymatou.payment.integration.model.UnifiedOrderResponse;
import com.ymatou.payment.integration.model.UserServiceResponse;
import com.ymatou.payment.integration.service.wxpay.UnifiedOrderService;
import com.ymatou.payment.integration.service.ymatou.UserService;

/**
 * WeiXin JSAPI 收单报文组织(15)
 * 
 * @author wangxudong 2016年5月14日 下午4:15:43
 *
 */
@Component
public class WeiXinJSAPIAcquireOrderServiceImpl implements AcquireOrderService {
    private static Logger logger = LoggerFactory.getLogger(WeiXinJSAPIAcquireOrderServiceImpl.class);

    @Resource
    private UnifiedOrderService unifiedOrderService;

    @Resource
    private SignatureService singatureService;

    @Resource
    private UserService userService;

    @Resource
    private InstitutionConfigManager instConfigManager;

    @Resource
    private IntegrationConfig integrationConfig;

    // JSON 序列化工具
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public AcquireOrderPackageResp acquireOrderPackage(Payment payment) {
        // 获取第三方机构配置
        InstitutionConfig instConfig = instConfigManager.getConfig(payment.getPaytype());

        // 调用微信统一下单接口
        String prepayId = weiXinPrepayId(instConfig, payment);

        // 拼装请求报文
        String reqForm = buildForm(prepayId, payment, instConfig);

        // 返回报文结果
        AcquireOrderPackageResp resp = new AcquireOrderPackageResp();
        resp.setResultType(AcquireOrderResultType.JSON);
        resp.setResult(reqForm);

        return resp;
    }

    /**
     * 获取到微信的预支付Id
     * 
     * @param instConfig
     * @param payment
     * @return
     */
    private String weiXinPrepayId(InstitutionConfig instConfig, Payment payment) {

        try {
            UnifiedOrderRequest request = new UnifiedOrderRequest();
            request.setAppid(instConfig.getAppId());
            request.setMch_id(instConfig.getMerchantId());
            request.setNonce_str(String.valueOf(new Random().nextInt(1000000000)));
            request.setBody(payment.getBussinessOrder().getSubject());
            request.setOut_trade_no(payment.getPaymentid());
            request.setTotal_fee((int) (payment.getPayprice().doubleValue() * 100));
            request.setSpbill_create_ip(payment.getBussinessOrder().getClientip());
            request.setNotify_url(
                    String.format("%s/notify/%s", integrationConfig.getYmtPaymentBaseUrl(), payment.getPaytype()));
            request.setTrade_type("JSAPI");
            request.setOpenid(getOpenId(payment));

            request.setTime_start(StringUtil.getDateFormatString("yyyyMMddHHmmss"));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.HOUR_OF_DAY, 2);
            request.setTime_expire(StringUtil.getDateFormatString("yyyyMMddHHmmss", calendar.getTime()));

            // 加签
            String sign = singatureService.signMessage(getMapFromObject(request), instConfig,
                    payment.getAcquireOrderReq().getMockHeader());
            request.setSign(sign);

            // 调用微信接口
            UnifiedOrderResponse response =
                    unifiedOrderService.doService(request, payment.getAcquireOrderReq().getMockHeader());
            if (response.getAppid().equals(instConfig.getAppId())
                    && response.getMch_id().equals(instConfig.getMerchantId())
                    && "SUCCESS".equals(response.getResult_code())
                    && "SUCCESS".equals(response.getReturn_code()))
                return response.getPrepay_id();
            else
                throw new BizException(ErrorCode.SERVER_SIDE_ACQUIRE_ORDER_FAILED,
                        "paymentid:" + payment.getPaymentid());
        } catch (Exception ex) {
            Log.error("call weixin unifed order failed", ex);
            throw new BizException(ErrorCode.SERVER_SIDE_ACQUIRE_ORDER_FAILED, "paymentid:" + payment.getPaymentid());
        }
    }

    /**
     * 获取到OpenId
     * 
     * @return
     */
    private String getOpenId(Payment payment) {
        try {
            Integer userId = payment.getBussinessOrder().getUserid();
            UserServiceResponse response = userService.doService(String.valueOf(userId), "Wap",
                    payment.getAcquireOrderReq().getMockHeader());
            if ("true".equals(response.getSuccess()))
                return response.getResult();
            else
                return null;

        } catch (IOException e) {
            Log.error("call user service for open id failed.", e);
            return null;
        }
    }

    /**
     * 将对象字段名映射成签名的字段
     * 
     * @param keyName
     * @return
     */
    private String signNameFormat(String keyName) {
        if ("AppID".equals(keyName))
            return "appId";
        else if ("SignType".equals(keyName))
            return "signType";
        else if ("TimeStamp".equals(keyName))
            return "timeStamp";
        else if ("NonceStr".equals(keyName))
            return "nonceStr";
        else if ("Sign".equals(keyName))
            return "sign";
        else if ("Package".equals(keyName))
            return "package";
        else
            return keyName;
    }

    /**
     * 将对象属性和值变成Map
     * 
     * @param obj
     * @return
     */
    private Map<String, String> getMapFromObject(Object obj) {
        Map<String, String> map = new HashMap<String, String>();
        Class<?> cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();
        try {
            for (Field f : fields) {
                f.setAccessible(true); // 设置成员变量可访问
                if (f.get(obj) != null && f.get(obj) != "") {
                    map.put(signNameFormat(f.getName()), f.get(obj).toString());
                }
            }
        } catch (IllegalAccessException e) {
            // 不会出现非法访问异常
        }
        return map;
    }

    /**
     * 构建报文
     * 
     * @param prepayId
     * @param payment
     * @return
     */
    private String buildForm(String prepayId, Payment payment, InstitutionConfig instConfig) {
        try {
            WeixinJSAPIOrderRequest request = new WeixinJSAPIOrderRequest();
            request.AppID = instConfig.getAppId();
            request.NonceStr = String.valueOf(new Random().nextInt(1000000000));
            request.TimeStamp = String.valueOf(new Date().getTime());
            request.Package = String.format("prepay_id=%s", prepayId);
            request.SignType = "MD5";

            Map<String, String> map = getMapFromObject(request);
            request.Sign = singatureService.signMessage(map, instConfig, payment.getAcquireOrderReq().getMockHeader());

            return objectMapper.writeValueAsString(request);
        } catch (Exception e) {
            Log.error("weixin jsapi buildFrom failed with paymentid:" + payment.getPaymentid(), e);
            throw new BizException(ErrorCode.FAIL, "build jsapi form failed");
        }
    }
}