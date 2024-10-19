package com.example.haibazotest.service;

import com.example.haibazotest.domain.dto.req.ProductReqDTO;
import com.example.haibazotest.domain.dto.res.ProductResDTO;

import java.util.List;

public interface IProductService {
    List<ProductResDTO> getAllProduct();

    ProductResDTO getProductById(Long id);

    void deleteProductById(Long id);

    ProductResDTO updateProductById(Long id, ProductReqDTO productReqDTO);

    ProductResDTO addProduct(ProductReqDTO productReqDTO);
}
