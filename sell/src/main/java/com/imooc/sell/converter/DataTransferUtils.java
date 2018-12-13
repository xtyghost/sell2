/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: DataTransferUtils
 * Author:   xutong
 * Date:     2018-12-11 13:13
 * Description: 数据转换工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.sell.converter;

import com.alibaba.fastjson.JSONArray;
import com.imooc.sell.constants.OrderStatusEnums;
import com.imooc.sell.constants.PayStatusEnums;
import com.imooc.sell.constants.ResultEnums;
import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.form.OrderForm;
import com.imooc.sell.pojo.OrderDetail;
import com.imooc.sell.pojo.OrderMaster;
import com.imooc.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈数据转换工具类〉
 *
 * @author xutong
 * @create 2018-12-11
 * @since 1.0.0
 */
@Slf4j
public class DataTransferUtils {
    /**
     * orderform转换位orderdto
     * @param form
     * @return
     */
    public static OrderDto OrderFormDtoOrderDto(OrderForm form) {
        OrderDto orderDto = new OrderDto();
        if (form != null) {
            orderDto.setBuyerName(form.getName());
            orderDto.setBuyerPhone(form.getPhone());
            orderDto.setBuyerAddress(form.getAddress());
            orderDto.setBuyerOpenid(form.getOpenid());
            List<OrderDetail> orderDetails;
            try {
                orderDetails = JSONArray.parseArray(form.getItems(), OrderDetail.class);
            } catch (Exception e) {
            log.error("[表单数据转换]orderForm中到items不是json格式，items={}",form.getItems());
              throw new SellException(ResultEnums.PARAM_ERROR);
            }
            orderDto.setDetails(orderDetails);
        }
        return orderDto;
    }

    /**
     * orderdto转换位ordermaster
     * @param orderDto
     * @return
     */
    public static OrderMaster transferOderDtoToOrderMaster(OrderDto orderDto) {
        OrderMaster orderMaster = new OrderMaster();
        //添加订单id
        orderDto.setOrderId(KeyUtil.getKey());
        BeanUtils.copyProperties(orderDto, orderMaster);
        //
        orderMaster.setOrderStatus(OrderStatusEnums.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnums.UNPAY.getCode());
        return orderMaster;
    }

    /**
     * 将ordermaster中到数据封装到orderdto
     * 但是Detail没有封装
     * @param orderMaster
     * @return
     */
    public static OrderDto transferOrderMasterToOrderDto(OrderMaster orderMaster){
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        return orderDto;
    }
}