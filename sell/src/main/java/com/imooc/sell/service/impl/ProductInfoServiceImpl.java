/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ProductInfoServiceImpl
 * Author:   xutong
 * Date:     2018-12-10 16:23
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.sell.service.impl;

import com.imooc.sell.constants.ResultEnums;
import com.imooc.sell.dto.CartDto;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.pojo.ProductInfo;
import com.imooc.sell.repository.ProductInfoRepository;
import com.imooc.sell.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author xutong
 * @create 2018-12-10
 * @since 1.0.0
 */
@Service
@Slf4j
public class ProductInfoServiceImpl implements ProductInfoService {
    @Autowired
    private ProductInfoRepository repository;

    @Override
    public List<ProductInfo> findAllByProductStatus(Integer status) {
        return repository.findAllByProductStatus(status);
    }

    @Override
    public Optional<ProductInfo> findById(String productId) {
        return repository.findById(productId);
    }

    @Override
    public List<ProductInfo> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductInfo> findALLByStatus(Integer categoryType) {
        return repository.findALLByProductStatusAndProductStatus(categoryType, 0);
    }

    @Override
    public Page<ProductInfo> findByPageable(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public void save(ProductInfo productInfo) {
        repository.save(productInfo);
    }

    @Override
    public void saveBatch(List<ProductInfo> productInfos) {
        repository.saveAll(productInfos);
    }

    @Override
    public List<ProductInfo> findAllById(List<String> ids) {
        return repository.findAllById(ids);
    }


    @Override
    @Transactional
    public void decrease(List<CartDto> cartDtos) {

        //批量查询product
        //修改数据
        //批量存入
        List<String> pids = cartDtos.stream().map(CartDto::getProductId).collect(Collectors.toList());
        List<ProductInfo> allById = repository.findAllById(pids);
        cartDtos.stream().forEach(cartDto -> {
            {
                AtomicBoolean flag = new AtomicBoolean(false);

                allById.stream().forEach(productInfo -> {
                    if (cartDto.getProductId().equals(productInfo.getProductId())) {
                        Integer result = cartDto.getProductQuantity() + productInfo.getProductStock();
                        if (result<0){
                            log.error("[decrease][减库存]库存不足，product={}",productInfo);
                            throw new SellException(ResultEnums.STOCK_NOT_ENOUTGH);
                        }
                        productInfo.setProductStock(cartDto.getProductQuantity() + productInfo.getProductStock());

                        flag.set(true);
                    }

                });
                if (flag.get()) {
                    log.error("[decrease][减库存]无法根据订单productid={}查询到订单", cartDto.getProductId());
                    throw new SellException(ResultEnums.CTFPBPID);
                }
            }
        });
        repository.saveAll(allById);

    }
}