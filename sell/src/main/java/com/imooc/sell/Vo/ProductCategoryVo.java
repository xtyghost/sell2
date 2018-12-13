/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ProductCategoryVo
 * Author:   xutong
 * Date:     2018-12-10 17:46
 * Description: 商品详情前提vo对象
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.sell.Vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈商品规格前台vo对象〉
 *
 * @author xutong
 * @create 2018-12-10
 * @since 1.0.0
 */
@Data
public class ProductCategoryVo {
 @NotNull
 @JsonProperty("name")
 private String categoryName;
 @NotNull
 @JsonProperty("type")
 private Integer categoryType;
 @JsonProperty("foods")
 private List<?> productInfos;
}