package com.example.haibazotest.domain.dto.res;

import com.example.haibazotest.domain.dto.res.order.OrderResDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ProductResDTO {
    Long id;
    String name;
    String description;
    Integer quantity;
    Double price;
    CategoryResDTO category;
}
