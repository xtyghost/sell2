package com.imooc.sell.service.impl;

import com.imooc.sell.constants.CategoryStatusEnums;
import com.imooc.sell.dto.CartDto;
import com.imooc.sell.pojo.ProductInfo;
import com.imooc.sell.service.ProductInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Temporal;
import java.math.BigDecimal;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoServiceImplTest {
    @Autowired
    private ProductInfoService productInfoService;

    @Test
    public void findById() {
        productInfoService.findById("1001").ifPresent(System.out::println);
    }

    @Test
    public void findAll() {
        productInfoService.findAll().forEach(System.out::println);
    }

    @Test
    public void findALLByStatus() {
        productInfoService.findALLByStatus(CategoryStatusEnums.OFF.getStatus()).forEach(System.out::println);
    }

    @Test
    public void findByPageable() {
        productInfoService.findByPageable(PageRequest.of(1, 2));
    }
    @Test
    public void save() {
        productInfoService.save(new ProductInfo("69", "fsds", new BigDecimal(4.3), 1, "sfsd", "sff", 1, 0, new Date(), new Date()));
    }

    @Test
    public void saveBatch() {
    }
    @Test
    public void decrease(){
        new CartDto();

    }
}