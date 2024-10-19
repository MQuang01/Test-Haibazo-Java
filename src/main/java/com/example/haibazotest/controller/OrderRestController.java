package com.example.haibazotest.controller;

import com.example.haibazotest.domain.dto.req.order.OrderReqDTO;
import com.example.haibazotest.domain.dto.res.ProductResDTO;
import com.example.haibazotest.service.IOrderService;
import com.example.haibazotest.until.ResponseData;
import com.example.haibazotest.until.SuccessCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderRestController {
    IOrderService orderService;
    @GetMapping
    public ResponseEntity<ResponseData<?>> getListOrder() {
        return ResponseEntity.ok(
                ResponseData.builder()
                        .code(SuccessCode.GET_SUCCESSFUL.getCode())
                        .message(SuccessCode.GET_SUCCESSFUL.getMessage())
                        .data(orderService.getAllOrder())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<?>> getOrderById(@PathVariable String id) {
        return ResponseEntity.ok(
                ResponseData.builder()
                        .code(SuccessCode.GET_SUCCESSFUL.getCode())
                        .message(SuccessCode.GET_SUCCESSFUL.getMessage())
                        .data(orderService.getOrderById(Long.valueOf(id)))
                        .build()
        );
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseData<?>> getOrderByUserId(@PathVariable String id) {
        return ResponseEntity.ok(
                ResponseData.builder()
                        .code(SuccessCode.GET_SUCCESSFUL.getCode())
                        .message(SuccessCode.GET_SUCCESSFUL.getMessage())
                        .data(orderService.getOrderByUserId(Long.valueOf(id)))
                        .build()
        );
    }
    @PostMapping
    public ResponseEntity<ResponseData<?>> createOrder(@RequestBody OrderReqDTO orderReqDTO) {
        return ResponseEntity.ok(
                ResponseData.builder()
                        .code(SuccessCode.CREATED.getCode())
                        .message(SuccessCode.CREATED.getMessage())
                        .data(orderService.createOrder(orderReqDTO))
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<?>> deleteOrderById(@PathVariable String id) {
        orderService.deleteOrderById(Long.valueOf(id));
        return ResponseEntity.ok(
                ResponseData.builder()
                        .code(SuccessCode.DELETE_SUCCESSFUL.getCode())
                        .message(SuccessCode.DELETE_SUCCESSFUL.getMessage())
                        .build()
        );
    }
}
