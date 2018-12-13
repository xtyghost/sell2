/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ProductInfoService
 * Author:   xutong
 * Date:     2018-12-10 16:17
 * Description: 商品信息服务层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.sell.service;

import com.imooc.sell.dto.CartDto;
import com.imooc.sell.pojo.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * 〈一句话功能简述〉<br> 
 * 〈商品信息服务层〉
 *
 * @author xutong
 * @create 2018-12-10
 * @since 1.0.0
 */
public interface ProductInfoService {
    /**
     * 根据商品状态查询
     *
     */
    List<ProductInfo>  findAllByProductStatus(Integer status);
    /**
     * 根据商品id查商品
     * @param productId
     * @return
     */
   Optional<ProductInfo> findById(String productId);

    /**
     * 查询所有的商品
     * @return
     */
   List<ProductInfo> findAll();

    /**
     * 根据商品类型查商品
     * @param categoryType
     * @return
     */
   List<ProductInfo> findALLByStatus(Integer categoryType);

    /**
     * 分页查询
     * @param pageable
     * @return
     */
   Page<ProductInfo> findByPageable(Pageable pageable);

    /**
     * 添加商品
     * @param productInfo
     */
   void save(ProductInfo productInfo);

    /**
     * 批量添加商品
     * @param productInfos
     */
   void saveBatch(List<ProductInfo> productInfos);

    /**
     * 根据商品id的集合查询所有商品信息
     * @param collect
     * @return
     */
    List<ProductInfo> findAllById(List<String> collect);


    /**
     * 扣库存
     */
    void decrease(List<CartDto> cartDtos);

}