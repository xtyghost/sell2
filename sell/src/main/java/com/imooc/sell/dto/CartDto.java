/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: CartDto
 * Author:   xutong
 * Date:     2018-12-13 13:13
 * Description: 订单Dto
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.sell.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 〈一句话功能简述〉<br>
 * 〈订单项Dto〉
 *
 * @author xutong
 * @create 2018-12-13
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    private String productId;
    private Integer productQuantity;
}