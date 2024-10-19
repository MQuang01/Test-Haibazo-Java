package com.example.haibazotest.mapper;

import com.example.haibazotest.domain.Order;
import com.example.haibazotest.domain.dto.req.order.OrderReqDTO;
import com.example.haibazotest.domain.dto.res.order.OrderResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    Order toOrder(OrderReqDTO orderReqDTO);
    OrderResDTO toOrderResDTO(Order order);
}
