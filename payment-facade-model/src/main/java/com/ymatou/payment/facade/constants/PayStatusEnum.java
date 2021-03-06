/**
 * (C) Copyright 2016 Ymatou (http://www.ymatou.com/).
 *
 * All rights reserved.
 */
package com.ymatou.payment.facade.constants;

/**
 * 支付单状态
 * 
 * @author wangxudong 2016年5月10日 下午6:36:21
 *
 */
public enum PayStatusEnum {
    Init(0, "初始化"),

    Paied(1, "已支付"),

    Refunded(2, "已退款"),

    Failed(-1, "支付失败"),

    UNKNOW(-99, "第三方返回SYSTEM_ERROR,支付状态未知");

    private int index;
    private String name;

    private PayStatusEnum(int index, String name) {
        this.index = index;
        this.name = name;
    }

    /**
     * 返回枚举索引值
     * 
     * @return
     */
    public int getIndex() {
        return this.index;
    }

    /**
     * 返回枚举名称
     * 
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * 枚举转换
     * 
     * @param index
     * @return
     */
    public static PayStatusEnum parse(int index) {
        switch (index) {
            case 0:
                return Init;
            case 1:
                return Paied;
            case 2:
                return Refunded;
            case -1:
                return Failed;
            default:
                return null;
        }
    }
}
