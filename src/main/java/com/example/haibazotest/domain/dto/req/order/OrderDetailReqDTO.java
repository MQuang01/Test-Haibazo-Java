package com.example.haibazotest.domain.dto.req.order;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Validated
public class OrderDetailReqDTO {
    @NotNull(message = "ProductId is required")
    String productId;
    @NotBlank(message = "Quantity is required")
    String quantity;
}
