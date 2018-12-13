package com.imooc.sell.repository;

import com.imooc.sell.pojo.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * 订单详情接口dao层
 */

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
    /**
     * 秒杀下单
     */
    @Query(value = "Call p_orderDetail_kill(:param1)", nativeQuery = true)
    Integer killAcount(@Param("param1") String v_product_id);

    List<OrderDetail> findAllByOrderId(String orderId);
}
