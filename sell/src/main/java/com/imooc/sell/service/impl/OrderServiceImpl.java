/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: OrderMasterServiceImpl
 * Author:   xutong
 * Date:     2018-12-10 17:57
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.sell.service.impl;

import com.imooc.sell.constants.OrderStatusEnums;
import com.imooc.sell.constants.ResultEnums;
import com.imooc.sell.converter.DataTransferUtils;
import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.pojo.OrderDetail;
import com.imooc.sell.pojo.OrderMaster;
import com.imooc.sell.pojo.ProductInfo;
import com.imooc.sell.repository.OrderDetailRepository;
import com.imooc.sell.repository.OrderMasterRepository;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.service.ProductInfoService;
import com.imooc.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static com.imooc.sell.converter.DataTransferUtils.transferOderDtoToOrderMaster;

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
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductInfoService productInfoService;


    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {
        //明确必须封装的对象
        //ordermaster
        OrderMaster orderMaster = transferOderDtoToOrderMaster(orderDto);
        String orderId = orderMaster.getOrderId();
        AtomicReference<BigDecimal> orderAmount = new AtomicReference<>(new BigDecimal(0));

        //orderdetaill
        List<OrderDetail> orderDetails = orderDto.getDetails();
        //product
        //product不能下架
        LinkedList<ProductInfo> productInfos = new LinkedList<>();

        //根据订单中的productid匹配商品中的数据，
        orderDetails.forEach(x -> {
            {
                //标志位用来标记是否通过productid查到商品信息
                AtomicBoolean flag = new AtomicBoolean(false);
                //订单中商品数量必须大于0
                if (x.getProductQuantity() < 0) {
                    log.error("[create][用户下单]订单中商品数量必须大于0,productId=={},ProductQuantity=={}", x.getProductQuantity(), x.getProductQuantity());
                    throw new SellException(ResultEnums.QUANTITYLESSTHANZORE);
                }
                //查询所有订单中的商品信息
                productInfoService.findAllById(orderDto.getDetails().stream().map(OrderDetail::getProductId).collect(Collectors.toList()))
                        .stream().forEach(e -> {
                    {
                        //商品是否下架
                        if (e.getProductStatus() != 0) {
                            log.error("[create][用户下单]不可以购买下架商品,getProductStatus()!={}", e.getProductStatus());
                            throw new SellException(ResultEnums.PRODUCTOFF);
                        }
                        if (x.getProductId().equals(e.getProductId())) {
                            flag.set(true);
                            Integer productStock = e.getProductStock();
                            Integer productQuantity = x.getProductQuantity();
                            if (productStock - productQuantity < 0) {
                                log.error("[create][用户下单]订单中商品数超过库存，订单库存=={},商品库存=={}", x.getProductQuantity(), e.getProductStock());
                            }
                            //封装商品信息
                            e.setProductStock(productStock - productQuantity);
                            productInfos.add(e);
                            //封装订单详情信息
                            x.setProductName(e.getProductName());
                            x.setProductIcon(e.getProductIcon());
                            x.setDetailId(KeyUtil.getKey());
                            x.setProductPrice(e.getProductPrice());
                            x.setOrderId(orderId);
                            //统计总的金额
                            BigDecimal add = orderAmount.get().add(e.getProductPrice().multiply(new BigDecimal(e.getProductStock())));
                            orderAmount.set(add);
                        }

                    }
                });
//            判断是否查询到product
                if (!flag.get()) {
                    log.error("[create][用户下单]没有根据productid=={},查询到对应商品", x.getProductId());
                    throw new SellException(ResultEnums.CTFPBPID);
                }


            }
        });

        orderMaster.setOrderAmount(orderAmount.get());
        //需要填充的数据，
        //数据库操作
        orderMasterRepository.save(orderMaster);
        orderDetailRepository.saveAll(orderDetails);
        //减少库存
        productInfoService.saveBatch(productInfos);

        return orderDto;
    }

    @Override
    public OrderDto cancel(String orderId) {
        //根据id查询订单，信息
        Optional<OrderMaster> byId = orderMasterRepository.findById(orderId);
        final OrderDto[] orderDto = new OrderDto[1];

        byId.ifPresent(e -> {
            if (e.getOrderStatus().equals(OrderStatusEnums.NEW.getCode())) {
                //返回库存
                List<OrderDetail> allByOrderId = orderDetailRepository.findAllByOrderId(orderId);
                //批量操作
                List<String> pids = allByOrderId.stream().map(OrderDetail::getProductId).collect(Collectors.toList());
                //查询所有商品
                List<ProductInfo> allById = productInfoService.findAllById(pids);
                //返回库存
                allByOrderId.stream().forEach(detail -> allById.stream().forEach(info -> {
                    if (detail.getProductId().equals(info.getProductId())) {
                        info.setProductStock(detail.getProductQuantity() + info.getProductStock());
                    }
                }));
                productInfoService.saveBatch(allById);
            }else {
                log.error("[cancel][取消订单]订单状态异常，OrderStatus={}",e.getOrderStatus());
                throw new SellException(ResultEnums.ORDER_STATUS_EXCEPTION);
            }
            //修改订单状态

            e.setOrderStatus(OrderStatusEnums.CANCEL.getCode());
            orderMasterRepository.save(e);
            orderDto[0] = DataTransferUtils.transferOrderMasterToOrderDto(e);
            //退款 TODO

        });
        return orderDto[0];
    }

    @Override
    public OrderDto finish(String orderid) {
        //查询订单
        Optional<OrderMaster> byId = orderMasterRepository.findById(orderid);
        OrderMaster orderMaster = byId.get();
        byId.ifPresent(e -> {
            //是否订单是新下单
            if (e.getOrderStatus().equals(OrderStatusEnums.NEW.getCode())){
                //修改订单转台
                e.setOrderStatus(OrderStatusEnums.FINISH.getCode());
                orderMasterRepository.save(e);
            }
        });
        return DataTransferUtils.transferOrderMasterToOrderDto(orderMaster);
    }

    @Override
    public OrderDto edit(OrderDto orderDto) {
        // TODO
        return null;
    }

    @Override
    public Optional<OrderDto> findByOrderId(String orderId) {
        Optional<OrderMaster> byId = orderMasterRepository.findById(orderId);
        OrderDto orderDto = new OrderDto();
        List<OrderDetail> allByOrderId = orderDetailRepository.findAllByOrderId(orderId);
        if (byId.isPresent()) {
            OrderMaster source = byId.get();
            BeanUtils.copyProperties(source, orderDto);
        }
        orderDto.setDetails(allByOrderId);
        //封装dto对象
        byId.ifPresent(master -> orderDto.setDetails(orderDetailRepository.findAllByOrderId(orderId)));
        return Optional.of(orderDto);
    }

    @Override
    public Page<OrderDto> findAll(Pageable pageable, String openid) {
        Page<OrderMaster> createTime = orderMasterRepository.findAllByBuyerOpenidOrderByUpdateTime(pageable, openid);
        //将masterlsit封装成dtolist
        List<OrderDto> orderDtos1 = transferOrderMastersToOrderDtos(createTime.getContent());
        PageImpl<OrderDto> orderDtos = new PageImpl<OrderDto>(orderDtos1);
        return orderDtos;
    }

    /**
     * 将数据库查询处到ordermasters转换为orderdtos
     *
     * @param orderMasters
     * @return
     */
    private List<OrderDto> transferOrderMastersToOrderDtos(List<OrderMaster> orderMasters) {
        //用于批量查询到orderids
        List<String> orderIds = orderMasters.stream().map(OrderMaster::getOrderId).collect(Collectors.toList());
        //将master转换位dto
        List<OrderDto> collect = orderMasters.stream().map(DataTransferUtils::transferOrderMasterToOrderDto).collect(Collectors.toList());
        //封装dto中到detail
        collect.forEach(Dto -> orderDetailRepository.findAllById(orderIds).forEach(Detail -> {
            {
                if (Dto.getOrderId().equals(Detail.getOrderId())) {
                    List<OrderDetail> details = Dto.getDetails();
                    if (details == null) {
                        details = new LinkedList<>();
                    }
                    details.add(Detail);
                }
            }
        }));
        return collect;
    }

    @Override
    public List<OrderDto> findAll(String buyerName) {
        //查询所有订单信息
        List<OrderMaster> collect = orderMasterRepository.findAllByBuyerName(buyerName);
        //根据ids查询orderdetail信息
        List<String> orderIds = collect.stream().map(OrderMaster::getOrderId).collect(Collectors.toList());
        //对象封装
        return transferOrderMastersToOrderDtos(collect);
    }
}