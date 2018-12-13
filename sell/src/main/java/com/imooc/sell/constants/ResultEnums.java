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
    STOCK_NOT_ENOUTGH(5,"库存不足")
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
