package com.example.haibazotest.domain.dto.req.user;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Validated
public class RegisterReqDTO {
    @NotBlank(message = "Username cannot be blank")
    String username;
    @NotBlank(message = "Password cannot be blank")
    String password;
    @NotBlank(message = "Full name cannot be blank")
    String fullName;
    @NotBlank(message = "Address cannot be blank")
    String address;
    @NotBlank(message = "Email cannot be blank")
    String email;
    @NotBlank(message = "Phone cannot be blank")
    String phone;
    @NotBlank(message = "Date of birth cannot be blank")
    String dob;
}
