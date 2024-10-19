package com.example.haibazotest.controller;

import com.example.haibazotest.domain.dto.req.ProductReqDTO;
import com.example.haibazotest.domain.dto.res.ProductResDTO;
import com.example.haibazotest.service.IProductService;
import com.example.haibazotest.until.ResponseData;
import com.example.haibazotest.until.SuccessCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductRestController {
    IProductService productService;

    @GetMapping
    public ResponseEntity<ResponseData<?>> getAllProduct() {
        return ResponseEntity.ok(
                ResponseData.builder()
                        .code(SuccessCode.GET_SUCCESSFUL.getCode())
                        .message(SuccessCode.GET_SUCCESSFUL.getMessage())
                        .data(productService.getAllProduct())
                        .build()
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<?>> getProductById(@PathVariable String id) {
        return ResponseEntity.ok(
                ResponseData.builder()
                        .code(SuccessCode.GET_SUCCESSFUL.getCode())
                        .message(SuccessCode.GET_SUCCESSFUL.getMessage())
                        .data(productService.getProductById(Long.valueOf(id)))
                        .build()
        );
    }
    @PostMapping
    public ResponseEntity<ResponseData<?>> createProduct(@RequestBody ProductReqDTO productReqDTO) {
        return ResponseEntity.ok(
                ResponseData.builder()
                        .code(SuccessCode.CREATED.getCode())
                        .message(SuccessCode.CREATED.getMessage())
                        .data(productService.addProduct(productReqDTO))
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<?>> deleteProductById(@PathVariable String id) {
        productService.deleteProductById(Long.valueOf(id));
        return ResponseEntity.ok(
                ResponseData.builder()
                        .code(SuccessCode.DELETE_SUCCESSFUL.getCode())
                        .message(SuccessCode.DELETE_SUCCESSFUL.getMessage())
                        .build()
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<?>> updateProductById(@PathVariable String id, @RequestBody ProductReqDTO productReqDTO) {
        return ResponseEntity.ok(
                ResponseData.builder()
                        .code(SuccessCode.UPDATE_SUCCESSFUL.getCode())
                        .message(SuccessCode.UPDATE_SUCCESSFUL.getMessage())
                        .data(productService.updateProductById(Long.valueOf(id), productReqDTO))
                        .build()
        );
    }
}
