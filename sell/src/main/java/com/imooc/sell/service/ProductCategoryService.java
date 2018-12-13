/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ProductCategoryService
 * Author:   xutong
 * Date:     2018-12-10 17:55
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.sell.service;

import com.imooc.sell.pojo.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈商品分类处理service〉
 *
 * @author xutong
 * @create 2018-12-10
 * @since 1.0.0
 */
public interface ProductCategoryService {

    List<ProductCategory> findAll();
}