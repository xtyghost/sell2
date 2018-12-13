/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: OrderDto
 * Author:   xutong
 * Date:     2018-12-11 11:25
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.sell.dto;

import com.imooc.sell.pojo.OrderDetail;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈订单dto类〉
 *
 * @author xutong
 * @create 2018-12-11
 * @since 1.0.0
 */
@Data
public class OrderDto {
    /**
     * 订单di
     */

    private String orderId;
    /**
     * 买家姓名
     */

    private String buyerName;
    /**
     * 买家手机号
     */

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private Integer orderStatus;

    private Integer payStatus;

    private Date createTime;

    private Date updateTime;
    /**
     * 订单详情
     */
    private List<OrderDetail> details=new LinkedList<>();
}