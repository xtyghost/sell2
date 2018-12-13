/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: ProductInfoController
 * Author:   xutong
 * Date:     2018-12-10 17:42
 * Description: 商品信息controller
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.sell.controller;

import com.imooc.sell.Vo.ProductCategoryVo;
import com.imooc.sell.Vo.ProductInfoVo;
import com.imooc.sell.Vo.ResultVo;
import com.imooc.sell.pojo.ProductInfo;
import com.imooc.sell.service.ProductCategoryService;
import com.imooc.sell.service.ProductInfoService;
import com.imooc.sell.utils.ResultVoUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈商品信息controller〉
 *
 * @author xutong
 * @create 2018-12-10
 * @since 1.0.0
 */
@RestController
@RequestMapping("/buyer/product")
@SuppressWarnings("unchecked")
public class ProductInfoController {
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 获取商品详情列表
     *
     * @return
     */
    @GetMapping("/list.json")
    public ResultVo getProductList() {
        LinkedList<ProductCategoryVo> productCategoryVos = new LinkedList<>();
        List<ProductInfo> allInfo = productInfoService.findAll();
        //封装商品详情
        productCategoryService.findAll().forEach(e -> {
            {
                ProductCategoryVo e1 = new ProductCategoryVo();
                BeanUtils.copyProperties(e, e1);
                //封装foods对象
                allInfo.forEach(x -> {
                    {
                        LinkedList<ProductInfoVo> foods = new LinkedList<>();
                        ProductInfoVo food = new ProductInfoVo();
                        BeanUtils.copyProperties(x, food);
                        foods.add(food);
                        e1.setProductInfos(foods);
                    }
                });
                productCategoryVos.add(e1);
            }
        });
        return ResultVoUtils.success(productCategoryVos);
    }

    @PostMapping("/saveOrUpdate")
    public ResultVo saveProductInfo(ProductInfo productInfo) {
        productInfoService.save(productInfo);
        return ResultVoUtils.success();
    }

}