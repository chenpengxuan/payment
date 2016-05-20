/**
 * (C) Copyright 2016 Ymatou (http://www.ymatou.com/).
 *
 * All rights reserved.
 */
package com.ymatou.payment.domain.pay.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ymatou.payment.domain.pay.model.BussinessOrder;
import com.ymatou.payment.domain.pay.model.PayStatus;
import com.ymatou.payment.facade.BizException;
import com.ymatou.payment.infrastructure.db.mapper.BussinessorderMapper;
import com.ymatou.payment.infrastructure.db.model.BussinessorderExample;
import com.ymatou.payment.infrastructure.db.model.BussinessorderPo;

/**
 * 商户订单仓储类
 *
 * @author wangxudong 2016年5月10日 下午5:06:39
 *
 */
@Component
public class BussinessOrderRepository {
    private static final Logger logger = LoggerFactory.getLogger(BussinessOrderRepository.class);

    @Resource
    private BussinessorderMapper mapper;

    @Resource
    private SqlSession sqlSession;

    /**
     * 根据商户订单（交易号）获取到商户订单信息
     *
     * @param orderId
     * @return
     */
    public BussinessOrder getByOrderId(String orderId) {
        BussinessorderExample example = new BussinessorderExample();
        example.createCriteria().andOrderidEqualTo(orderId);
        List<BussinessorderPo> opList = sqlSession.selectList("ext-ppBussinessorder.selectByExample", example);

        if (opList.size() == 0)
            return null;


        return BussinessOrder.convertFromPo(opList.get(0));
    }

    /**
     * 保存商户订单
     *
     * @param po
     * @return
     */
    public int insert(BussinessorderPo po) {
        return sqlSession.insert("ext-ppBussinessorder.insert", po);
    }

    /**
     * 更新商户订单状态
     * 
     * @param bussinessorderid
     * @param orderStatus
     */
    public void updateOrderStatus(String bussinessorderid, int orderStatus) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bussinessorderid", bussinessorderid);
        params.put("orderstatus", orderStatus);

        int updated = sqlSession.update("ext-ppBussinessorder.updateOrderStatus", params);
        if (updated <= 0) {
            throw new BizException("Bussinessorder not exist");
        }
    }

    /**
     * 根据Id获取BussinessOrder
     * 
     * @param bussinessorderid
     * @return
     */
    public BussinessOrder getBussinessOrderById(String bussinessorderid) {
        BussinessorderExample example = new BussinessorderExample();
        example.createCriteria().andBussinessorderidEqualTo(bussinessorderid);
        List<BussinessorderPo> opList = sqlSession.selectList("ext-ppBussinessorder.selectByExample", example);
        if (opList == null || opList.size() == 0)
            return null;
        return BussinessOrder.convertFromPo(opList.get(0));
    }

    /**
     * 根据traceNo找到可以退款的订单
     * 
     * @param traceNo
     * @param orderStatus
     * @param valideDate
     * @return
     */
    public BussinessOrder getBussinessOrderCanRefund(String tradeNo, Integer orderStatus, Date validDate) {
        BussinessorderExample example = new BussinessorderExample();
        example.createCriteria().andOrderidEqualTo(tradeNo)
                .andOrderstatusEqualTo(orderStatus)
                .andCreatedtimeGreaterThanOrEqualTo(validDate);
        List<BussinessorderPo> pos = sqlSession.selectList("ext-ppBussinessorder.selectByExample", example);
        if (pos == null || pos.size() == 0) {
            return null;
        }
        return BussinessOrder.convertFromPo(pos.get(0));
    }
}