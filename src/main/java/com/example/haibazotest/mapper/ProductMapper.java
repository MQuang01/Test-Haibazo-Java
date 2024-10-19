package com.example.haibazotest.mapper;

import com.example.haibazotest.domain.Product;
import com.example.haibazotest.domain.dto.req.ProductReqDTO;
import com.example.haibazotest.domain.dto.res.ProductResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductResDTO toProductResDTO(Product product);

    Product toProduct(ProductReqDTO productReqDTO);
}
