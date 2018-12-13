package com.imooc.sell.service.impl;

import com.imooc.sell.constants.OrderStatusEnums;
import com.imooc.sell.constants.PayStatusEnums;
import com.imooc.sell.converter.DataTransferUtils;
import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.pojo.OrderMaster;
import com.imooc.sell.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMasterServiceImplTest {
    @Autowired
    private OrderService service;

    @Test
    public void save() {
        OrderMaster entity = new OrderMaster();
        entity.setOrderId("dssf");
        entity.setOrderAmount(new BigDecimal(1));
        entity.setBuyerAddress("sdf");
        entity.setBuyerName("sd");
        entity.setBuyerOpenid("vp34234");
        entity.setBuyerPhone("243242");
        entity.setOrderStatus(OrderStatusEnums.NEW.getCode());
        entity.setPayStatus(PayStatusEnums.PAY.getCode());
        OrderDto orderDto = DataTransferUtils.transferOrderMasterToOrderDto(entity);
        service.create(orderDto);
    }


    @Test
    public void findByOrderId() {
        System.out.println(service.findByOrderId("-471936482944759107"));
    }

    @Test
    public void findAll() {

        service.findAll(PageRequest.of(1, 2), " ew3euwhd7sjw9diwkq");
    }

    @Test
    public void findAll1() {
        service.findAll("张三").forEach(System.out::println);
    }
}