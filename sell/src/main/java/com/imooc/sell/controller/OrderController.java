/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: OrderController
 * Author:   xutong
 * Date:     2018-12-11 11:06
 * Description: 订单控制层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.imooc.sell.controller;

import com.imooc.sell.Vo.ResultVo;
import com.imooc.sell.constants.ResultEnums;
import com.imooc.sell.dto.OrderDto;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.form.OrderForm;
import com.imooc.sell.service.OrderService;
import com.imooc.sell.utils.ResultVoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.imooc.sell.converter.DataTransferUtils.OrderFormDtoOrderDto;

/**
 * 〈一句话功能简述〉<br>
 * 〈订单控制层〉
 *
 * @author xutong
 * @create 2018-12-11
 * @since 1.0.0
 */
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderMasterService;

    @PostMapping("create")
    public ResultVo createOrder(@Valid OrderForm form, BindingResult bindingResult) {
        //参数校验
        if (bindingResult.hasErrors()) {
            log.error("[createOrder][表单创建]参数不正确，orderForm={}", form);
            throw new SellException(ResultEnums.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
        }
        //将form封装层dto
        //对象信息转换
        OrderDto orderDto1 = OrderFormDtoOrderDto(form);
        if (orderDto1.getDetails() == null || orderDto1.getDetails().size() <= 0) {
            log.error("[createOrder][表单信息转换]表单中的订单详情为空，orderDto1.getDetails()={}", orderDto1.getDetails());
            throw new SellException(ResultEnums.DETAILEMPTY);

        }
        OrderDto orderDto = orderMasterService.create(orderDto1);
        //封装map对象
        HashMap<String, String> result = new HashMap<>();
        result.put("orderId", orderDto.getOrderId());
        return ResultVoUtils.success(result);
    }

    /**
     * 查询订单列表
     *
     * @param openid
     * @param page
     * @return
     */
    @GetMapping("list")
    public ResultVo findOrderListByOpenid(@RequestParam("openid") String openid, @RequestParam(value = "page",defaultValue = "0") Integer page, @RequestParam(value = "size",defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)){
            log.error("[findOrderListByOpenid][订单列表查询]openid不能为空");
            throw new SellException(ResultEnums.OPENDID_NOT_EMPTY);
        }

        Page<OrderDto> all = orderMasterService.findAll(PageRequest.of(page, size), openid);
        return ResultVoUtils.success(all.getContent());
    }
    /**
     * 查询订单详情
     */
    @GetMapping("/detail")
    public ResultVo<OrderDto> detail(@RequestParam("openid")String openid,@RequestParam("orderId")String orderId){
        //TODO 不安全
        Optional<OrderDto> byOrderId = orderMasterService.findByOrderId(orderId);
        OrderDto orderDto = byOrderId.get();

        return ResultVoUtils.success(orderDto);
    }
    @GetMapping("/cancel")
    public ResultVo cancel(@RequestParam("openid")String openid,@RequestParam("orderId")String orderId){
        //TODO 不安全
        OrderDto orderDto = orderMasterService.cancel(orderId);


        return ResultVoUtils.success(orderDto);
    }
}