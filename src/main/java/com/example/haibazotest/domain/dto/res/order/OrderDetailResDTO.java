package com.example.haibazotest.domain.dto.res.order;

import com.example.haibazotest.domain.dto.res.ProductResDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class OrderDetailResDTO {
    Long id;
    ProductResDTO product;
    Integer quantity;
    Double price;
}
