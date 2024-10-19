package com.example.haibazotest.mapper;

import com.example.haibazotest.domain.OrderDetail;
import com.example.haibazotest.domain.dto.req.order.OrderDetailReqDTO;
import com.example.haibazotest.domain.dto.res.order.OrderDetailResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    OrderDetailMapper INSTANCE = Mappers.getMapper(OrderDetailMapper.class);
    OrderDetail toOrderDetail(OrderDetailReqDTO orderDetailReqDTO);
    OrderDetailResDTO toOrderDetailResDTO(OrderDetail orderDetail);
}
