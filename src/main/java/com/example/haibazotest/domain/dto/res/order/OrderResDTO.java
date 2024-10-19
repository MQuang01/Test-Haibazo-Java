package com.example.haibazotest.domain.dto.res.order;

import com.example.haibazotest.domain.enumeration.EStatusOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class OrderResDTO {
    Long id;
    String description;
    String address;
    String phone;
    String email;
    LocalDateTime createdDate;
    EStatusOrder statusOrder;
    double total;
    List<OrderDetailResDTO> listOrderDetail;
}
