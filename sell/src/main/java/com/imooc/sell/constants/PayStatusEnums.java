/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: PayStatusEnums
 * Author:   xutong
 * Date:     2018-12-11 14:02
 * Description: 支付转台enums
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.sell.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 〈一句话功能简述〉<br>
 * 〈支付转台enums〉
 *
 * @author xutong
 * @create 2018-12-11
 * @since 1.0.0
 */
@Getter
@AllArgsConstructor
public enum PayStatusEnums {
    UNPAY(0,"订单未支付"),
    PAY(1,"定案已支付")
    ;
    /**
     * 支付码
     */
    private Integer code;
    /**
     * 支付信息
     */
    private String msg;
}