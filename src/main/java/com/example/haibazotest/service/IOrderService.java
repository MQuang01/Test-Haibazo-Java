package com.example.haibazotest.service;

import com.example.haibazotest.domain.dto.req.order.OrderReqDTO;
import com.example.haibazotest.domain.dto.res.order.OrderResDTO;

import java.util.List;

public interface IOrderService {
    List<OrderResDTO> getAllOrder();
    OrderResDTO getOrderById(Long id);
    OrderResDTO createOrder(OrderReqDTO orderReqDTO);
    void deleteOrderById(Long id);

    List<OrderResDTO> getOrderByUserId(Long aLong);
}
