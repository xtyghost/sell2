package com.imooc.sell.repository;

import com.imooc.sell.pojo.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * 客户表
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
    /**
     * 根据买家姓名
     * 排序方式
     * 进行分页查询订单信息
     * @param pageable
     * @param Openid
     * @param
     * @return
     */
    Page<OrderMaster> findAllByBuyerOpenidOrderByUpdateTime(Pageable pageable, String Openid);
    /**
     * 根据买家信息查询所有订单信息
     *
     */

    List<OrderMaster> findAllByBuyerName(String buyerName);
}
