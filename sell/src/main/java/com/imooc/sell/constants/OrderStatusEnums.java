/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: OrderStatusEnums
 * Author:   xutong
 * Date:     2018-12-11 13:55
 * Description: 订单状态常量
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.sell.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 〈一句话功能简述〉<br>
 * 〈订单状态常量〉
 *
 * @author xutong
 * @create 2018-12-11
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnums {
    NEW(0, "新下单"),
    FINISH(1, "订单完结"),
    CANCEL(2, "订单取消");

    /**
     * 订单编号
     */
    private Integer code;
    /**
     * 状态信息
     */
    private String msg;
}