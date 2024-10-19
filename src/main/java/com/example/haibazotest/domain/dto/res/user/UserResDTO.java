package com.example.haibazotest.domain.dto.res.user;

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
public class UserResDTO {
    Long id;
    String username;
    String fullName;
    String address;
    String email;
    String phone;
    String dob;
    List<OrderResDTO> listOrder;
}
