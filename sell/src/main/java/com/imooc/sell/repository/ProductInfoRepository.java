/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ProductInfoRepository
 * Author:   xutong
 * Date:     2018-12-07 15:51
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.sell.repository;

import com.imooc.sell.pojo.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author xutong
 * @create 2018-12-07
 * @since 1.0.0
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    List<ProductInfo> findALLByProductStatusAndProductStatus(Integer categoryType,Integer productStaus);


    List<ProductInfo> findAllByProductStatus(Integer status);
}