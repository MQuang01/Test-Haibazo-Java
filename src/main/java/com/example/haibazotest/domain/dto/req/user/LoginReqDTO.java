package com.example.haibazotest.domain.dto.req.user;

import jakarta.validation.constraints.NotBlank;
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
public class LoginReqDTO {
    @NotBlank(message = "Username cannot be blank")
    String username;
    @NotBlank(message = "Password cannot be blank")
    String password;
}
