/*
 * (C) Copyright 2016 Ymatou (http://www.ymatou.com/). All rights reserved.
 */
package com.ymatou.payment.domain.refund.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ymatou.payment.domain.refund.repository.RefundPository;
import com.ymatou.payment.facade.model.QueryRefundDetail;
import com.ymatou.payment.facade.model.QueryRefundRequest;
import com.ymatou.payment.infrastructure.db.model.RefundrequestPo;

/**
 * 
 * @author qianmin 2016年5月13日 下午3:40:40
 * 
 */
@Component
public class QueryRefundServiceImpl implements QueryRefundService {

    private static final Logger logger = LoggerFactory.getLogger(QueryRefundServiceImpl.class);

    @Autowired
    private RefundPository refundPository;

    @Override
    public List<QueryRefundDetail> queryRefundRequest(QueryRefundRequest req) {
        Integer pageIndex = req.getPageIndex() < 1 ? 1 : req.getPageIndex();
        Integer pageSize = req.getPageSize() < 1 ? 20 : req.getPageSize();
        String orderId = StringUtils.isBlank(req.getKey()) ? null : req.getKey();
        String refundStatusQuery = generateRefundStatusQuery(req.getRefundStatus());

        HashMap<String, Object> query = new HashMap<>();
        query.put("pageIndex", pageIndex);
        query.put("pageSize", pageSize);
        query.put("approveStatus", req.getApproveStatus());
        query.put("orderId", orderId);
        query.put("refundStatus", refundStatusQuery);
        logger.info("query refundRequest options: {}", query.toString());

        List<RefundrequestPo> refundrequestPos = refundPository.queryRefundRequest(query);
        logger.info("query refundRequest result count: {}", String.valueOf(refundrequestPos.size()));

        return generateRefundDetail(refundrequestPos);
    }

    private String generateRefundStatusQuery(List<Integer> refundStatusList) {
        if (refundStatusList == null || refundStatusList.size() == 0) {
            refundStatusList = Arrays.asList(new Integer[] {-2, -1, 0, 1, 2, 3, 4});
        }
        StringBuilder sb = new StringBuilder().append("(");
        for (Integer rs : refundStatusList) {
            sb.append(rs).append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");

        return sb.toString();
    }

    /**
     * 根据RefundRequest PO生成应答detail
     * 
     * @param refundrequestPos
     * @return
     */
    private List<QueryRefundDetail> generateRefundDetail(List<RefundrequestPo> refundrequestPos) {
        List<QueryRefundDetail> queryRefundDetails = new ArrayList<>(refundrequestPos.size());

        int index = 0;
        for (RefundrequestPo rq : refundrequestPos) {
            logger.info("RefundRequestPos[{}]: {}", index++, rq);
            QueryRefundDetail detail = new QueryRefundDetail();
            detail.setTradeNo(rq.getTradeno());
            detail.setOrderId(rq.getOrderid());
            detail.setAppId(rq.getAppid());
            detail.setPayType(rq.getPaytype());
            detail.setRefundAmount(rq.getRefundamount());
            detail.setCurrencyType(rq.getCurrencytype());
            detail.setApproveStatus(rq.getApprovestatus());
            detail.setApprovedTime(rq.getApprovedtime());
            detail.setCreatedTime(rq.getCreatedtime());
            detail.setApprovedUser(rq.getApproveduser());
            detail.setRefundStatus(rq.getRefundstatus());
            detail.setRefundTime(rq.getRefundtime());
            detail.setRefundBatchNo(rq.getRefundbatchno());
            detail.setPaymentId(rq.getPaymentid());
            detail.setPayChannel(Integer.valueOf(
                    refundPository.convertPayTypeToPayChannel(rq.getPaytype())));
            detail.setTradeType(rq.getTradetype());

            queryRefundDetails.add(detail);
        }

        return queryRefundDetails;
    }
}