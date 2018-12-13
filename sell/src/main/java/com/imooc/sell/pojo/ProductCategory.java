/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ProductCategory
 * Author:   xutong
 * Date:     2018-12-06 13:33
 * Description: 商品购物车实体
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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈商品购物车实体〉
 *
 * @author xutong
 * @create 2018-12-06
 * @since 1.0.0
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate(true)
@DynamicInsert
public class ProductCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer categoryId;
    @NotNull
    private String categoryName;
    @NotNull
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;
}