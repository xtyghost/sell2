package com.imooc.sell.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 使用enum统一异常的格式
 * 方便处理
 */
@Getter
@AllArgsConstructor
public enum ResultEnums {
    PARAM_ERROR(1,"参数校验不通过"),
    DETAILEMPTY(2,"订单详情信息不能为空"),
    PRODUCTOFF(3,"商品已经下架"),
    QUANTITYLESSTHANZORE(4,"订单中商品数量必须大于0"),
    CTFPBPID(5,"无法查询到对应商品信息根据商品productId"),
    STOCK_NOT_ENOUTGH(6,"库存不足"),
    OPENDID_NOT_EMPTY(7,"openid不能为空"),
    ORDER_OWNER_ERROR(8,"该订单不属于此用户"),
    ORDER_NOT_EXSIT(9,"订单不存在"),
    ORDER_STATUS_EXCEPTION(10,"订单状态异常"),
    ;

    /**
     * 异常编号
     */
    private Integer code;
    /**
     * 异常信息
     */
    private String msg;
}
