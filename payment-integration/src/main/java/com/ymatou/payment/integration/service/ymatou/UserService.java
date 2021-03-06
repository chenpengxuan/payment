/*
 * (C) Copyright 2016 Ymatou (http://www.ymatou.com/). All rights reserved.
 */
package com.ymatou.payment.integration.service.ymatou;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.ymatou.payment.integration.IntegrationConfig;
import com.ymatou.payment.integration.common.HttpClientUtil;
import com.ymatou.payment.integration.common.constants.Constants;
import com.ymatou.payment.integration.model.UserServiceResponse;

/**
 * ymatou用户服务
 * 
 * @author qianmin 2016年5月9日 上午10:42:18
 *
 */
@Component
public class UserService implements InitializingBean {


    private CloseableHttpClient httpClient;

    @Autowired
    private IntegrationConfig integrationConfig;

    /**
     * 用户服务
     * 
     * @param userId
     * @param reqSource
     * @param header 请求header
     * @return UserServiceResponse
     * @throws IOException
     */
    public UserServiceResponse doService(String userId, String reqSource, HashMap<String, String> header)
            throws IOException {
        String url = new StringBuilder(128).append(integrationConfig.getYmtUserServiceUrl(header))
                .append("?UserId=").append(userId).append("&RegSource=").append(reqSource).toString();

        String result = HttpClientUtil.sendGet(url, header, httpClient);

        UserServiceResponse userServiceResponse = JSON.parseObject(result, UserServiceResponse.class);
        return userServiceResponse;
    }

    @Override
    public void afterPropertiesSet() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setDefaultMaxPerRoute(Constants.DEFAULT_MAX_PER_ROUTE);
        cm.setMaxTotal(Constants.MAX_TOTAL);

        httpClient = HttpClients.custom().setConnectionManager(cm).build();
    }
}
