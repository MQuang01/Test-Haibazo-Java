package com.example.haibazotest.domain.dto.req.order;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Validated
public class OrderReqDTO {
    @NotBlank(message = "userId cannot be blank")
    String userId;
    String description;
    @NotBlank(message = "Address cannot be blank")
    String address;
    @NotBlank(message = "Phone cannot be blank")
    String phone;
    String email;
    List<OrderDetailReqDTO> listOrderDetail;
}
