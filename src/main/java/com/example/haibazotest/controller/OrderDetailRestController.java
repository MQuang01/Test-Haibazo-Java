package com.example.haibazotest.controller;

import com.example.haibazotest.service.IOrderDetailService;
import com.example.haibazotest.until.ResponseData;
import com.example.haibazotest.until.SuccessCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order_details")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderDetailRestController {
    IOrderDetailService orderDetailService;
    @GetMapping("/order/{id}")
    public ResponseEntity<ResponseData<?>> getOrderDetailByOrderId(@PathVariable Long id) {
        return ResponseEntity.ok(
                ResponseData.builder()
                        .code(SuccessCode.GET_SUCCESSFUL.getCode())
                        .message(SuccessCode.GET_SUCCESSFUL.getMessage())
                        .data(orderDetailService.findByOrderId(id))
                        .build()
        );
    }
}
