/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: OrderMaster
 * Author:   xutong
 * Date:     2018-12-06 13:05
 * Description: 订单表
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.sell.pojo;

import com.imooc.sell.constants.OrderStatusEnums;
import com.imooc.sell.constants.PayStatusEnums;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈订单表〉
 *
 * @author xutong
 * @create 2018-12-06
 * @since 1.0.0
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@DynamicInsert
public class OrderMaster {
    /**
     * 订单id
     */
    @Id
    @NotNull
    private String orderId;
    /**
     * 买家姓名
     */
    @NotNull
    private String buyerName;
    /**
     * 买家手机号
     */
    @NotNull
    private String buyerPhone;
    /**
     * 买家地址
     */
    @NotNull
    private String buyerAddress;
    /**
     * 买家openid
     */
    @NotNull
    private String buyerOpenid;
    /**
     * 订单中金额
     */
    @NotNull
    private BigDecimal orderAmount;
    /**
     * 订单状态
     */
    @NotNull
    private Integer orderStatus= OrderStatusEnums.NEW.getCode();
    /**
     * 字符转台
     */
    @NotNull
    private Integer payStatus= PayStatusEnums.UNPAY.getCode();
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
}