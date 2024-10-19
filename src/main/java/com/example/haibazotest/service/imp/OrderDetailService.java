package com.example.haibazotest.service.imp;

import com.example.haibazotest.domain.dto.res.order.OrderDetailResDTO;
import com.example.haibazotest.mapper.OrderDetailMapper;
import com.example.haibazotest.respository.IOrderDetailRepository;
import com.example.haibazotest.service.IOrderDetailService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderDetailService implements IOrderDetailService {
    IOrderDetailRepository orderDetailRepository;
    OrderDetailMapper orderDetailMapper = OrderDetailMapper.INSTANCE;
    @Override
    public List<OrderDetailResDTO> findByOrderId(Long orderId) {
        return orderDetailRepository.findByOrderId(orderId).stream().map(orderDetailMapper::toOrderDetailResDTO).toList();
    }
}
