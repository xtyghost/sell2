/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: OrderDetail
 * Author:   xutong
 * Date:     2018-12-06 12:55
 * Description: 订单详情表
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.sell.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br>
 * 〈订单详情表〉
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
public class OrderDetail {
    /**
     * 订单详情id
     */
    @Id
    @NotNull
    private String detailId;
    /**
     * 订单id
     */
    @NotNull
    private String orderId;
    /**
     * 商品id
     */
    @NotNull
    private String productId;
    /**
     * 商品名
     */
    @NotNull
    private String productName;
    /**
     * 商品价格
     */
    @NotNull
    private BigDecimal productPrice;
    /**
     * 商品数量
     */
    @NotNull
    private Integer productQuantity;
    /**
     * 商品图片
     */
    private String productIcon;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}