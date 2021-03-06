/**
 * (C) Copyright 2016 Ymatou (http://www.ymatou.com/).
 *
 * All rights reserved.
 */
package com.ymatou.payment.domain.channel.service.acquireorder;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ymatou.payment.domain.channel.InstitutionConfig;
import com.ymatou.payment.domain.channel.InstitutionConfigManager;
import com.ymatou.payment.domain.channel.model.AcquireOrderPackageResp;
import com.ymatou.payment.domain.channel.service.AcquireOrderService;
import com.ymatou.payment.domain.channel.service.SignatureService;
import com.ymatou.payment.domain.pay.model.Payment;
import com.ymatou.payment.facade.BizException;
import com.ymatou.payment.facade.ErrorCode;
import com.ymatou.payment.facade.constants.AcquireOrderResultTypeEnum;
import com.ymatou.payment.infrastructure.util.StringUtil;
import com.ymatou.payment.integration.IntegrationConfig;
import com.ymatou.payment.integration.model.UnifiedOrderRequest;
import com.ymatou.payment.integration.model.UnifiedOrderResponse;
import com.ymatou.payment.integration.service.wxpay.UnifiedOrderService;
import com.ymatou.payment.integration.service.ymatou.UserService;

/**
 * WeiXin PC 收单报文组织(16)
 * 
 * @author wangxudong 2016年5月14日 下午4:15:43
 *
 */
@Component
public class WeiXinPCAcquireOrderServiceImpl implements AcquireOrderService {
    private static Logger logger = LoggerFactory.getLogger(WeiXinPCAcquireOrderServiceImpl.class);

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
    public AcquireOrderPackageResp acquireOrderPackage(Payment payment, HashMap<String, String> mockHeader) {
        // 获取第三方机构配置
        InstitutionConfig instConfig = instConfigManager.getConfig(payment.getPayType());

        // 调用微信统一下单接口
        String codeUrl = weiXinCodeUrl(instConfig, payment, mockHeader);

        // 返回报文结果
        AcquireOrderPackageResp resp = new AcquireOrderPackageResp();
        resp.setResultType(AcquireOrderResultTypeEnum.QRCode);
        resp.setResult(codeUrl);

        return resp;
    }

    /**
     * 获取到微信的二维码链接(code_url)
     * 
     * @param instConfig
     * @param payment
     * @return
     */
    private String weiXinCodeUrl(InstitutionConfig instConfig, Payment payment, HashMap<String, String> mockHeader) {

        try {
            UnifiedOrderRequest request = new UnifiedOrderRequest();
            request.setAppid(instConfig.getAppId());
            request.setMch_id(instConfig.getMerchantId());
            request.setNonce_str(String.valueOf(new Random().nextInt(1000000000)));
            request.setBody(payment.getBussinessOrder().getSubject());
            request.setOut_trade_no(payment.getPaymentId());
            request.setTotal_fee((int) (payment.getPayPrice().getCent()));
            request.setSpbill_create_ip(payment.getBussinessOrder().getClientIp());
            request.setNotify_url(
                    String.format("%s/notify/%s", integrationConfig.getYmtPaymentBaseUrl(), payment.getPayType()));
            request.setTrade_type("NATIVE");
            // request.setOpenid(getOpenId(payment, mockHeader));

            request.setTime_start(StringUtil.getDateFormatString("yyyyMMddHHmmss"));
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.HOUR_OF_DAY, 2);
            request.setTime_expire(StringUtil.getDateFormatString("yyyyMMddHHmmss", calendar.getTime()));

            // 加签
            String sign = singatureService.signMessage(getMapFromObject(request), instConfig, mockHeader);
            request.setSign(sign);

            // 调用微信接口
            UnifiedOrderResponse response = unifiedOrderService.doService(request, mockHeader);
            if (instConfig.getAppId().equals(response.getAppid())
                    && response.getMch_id().equals(instConfig.getMerchantId())
                    && "SUCCESS".equals(response.getResult_code())
                    && "SUCCESS".equals(response.getReturn_code())) {
                return response.getCode_url();
            } else {
                throw new Exception(response.getReturn_msg());
            }
        } catch (BizException e) {
            throw e;
        } catch (Exception ex) {
            logger.error("call weixin pc unifed order failed. PaymentId: " + payment.getPaymentId(), ex);
            throw new BizException(ErrorCode.SERVER_SIDE_ACQUIRE_ORDER_FAILED,
                    "paymentid:" + payment.getPaymentId() + "|" + ex.getLocalizedMessage(), ex);
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
                // if (f.get(obj) != null && f.get(obj) != "") {
                if (f.get(obj) != null && !StringUtils.isEmpty(f.get(obj).toString())) {
                    map.put(signNameFormat(f.getName()), f.get(obj).toString());
                }
            }
        } catch (IllegalAccessException e) {
            // 不会出现非法访问异常
        }
        return map;
    }
}
