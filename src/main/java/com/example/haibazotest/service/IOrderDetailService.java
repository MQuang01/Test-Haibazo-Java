package com.example.haibazotest.service;

import com.example.haibazotest.domain.dto.res.order.OrderDetailResDTO;

import java.util.List;

public interface IOrderDetailService {
    List<OrderDetailResDTO> findByOrderId(Long orderId);
}
