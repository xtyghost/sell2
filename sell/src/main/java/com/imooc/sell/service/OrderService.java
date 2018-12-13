package com.imooc.sell.service;

import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.pojo.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * 订单主体service
 */
public interface OrderService {
    /**
     * 添加订单
     * @param
     */
     OrderDto create(OrderDto orderDto);

    /**
     * 取消订单
     * @param orderid
     * @return
     */

    OrderDto cancel(String orderid);

    /**
     * 完结订单
     * @param orderid
     * @return
     */
    OrderDto finish(String orderid);
    /**
     * 订单修改
     *
     */
    OrderDto edit(OrderDto orderDto);
    /**
     * 根据订单主键查询订单
     * @param orderId
     * @return
     */
    Optional<OrderDto> findByOrderId(String orderId);

    /**
     * 分页查寻订单
     * @param pageable
     * @return
     */
    Page<OrderDto> findAll(Pageable pageable, String openid);

    /**
     * 查询所有订单信息
     * @return
     */
    List<OrderDto> findAll(String buyerName);
}
