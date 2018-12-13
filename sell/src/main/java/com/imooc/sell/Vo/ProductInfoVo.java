/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ProductInfoVo
 * Author:   xutong
 * Date:     2018-12-11 08:32
 * Description: 商品详情vo对象
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.sell.Vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 〈一句话功能简述〉<br> 
 * 〈商品详情vo对象〉
 *
 * @author xutong
 * @create 2018-12-11
 * @since 1.0.0
 */
@Data
public class ProductInfoVo {
    @NotNull
    @JsonProperty("id")
    private String productId;
    @NotNull
    @JsonProperty("name")
    private String productName;
    @NotNull
    @JsonProperty("price")
    private BigDecimal productPrice;
    @JsonProperty("description")
    private String productDescription;
    @JsonProperty("icon")
    private String productIcon;
}