/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: OrderForm
 * Author:   xutong
 * Date:     2018-12-11 12:32
 * Description: 订单提交表单
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.sell.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * 〈一句话功能简述〉<br>
 * 〈订单提交表单〉
 *
 * @author xutong
 * @create 2018-12-11
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderForm {
    @NotEmpty(message = "买家姓名不能为空")
    private String name;
    @NotEmpty(message = "买家手机号码不能为空")
    private String phone;
    @NotEmpty(message = "买家地址不能为空")
    private String address;
    @NotEmpty(message = "买家oppenid不能为空")
    private String openid;
    @NotEmpty(message = "订单不能为空")
    private String items;
}