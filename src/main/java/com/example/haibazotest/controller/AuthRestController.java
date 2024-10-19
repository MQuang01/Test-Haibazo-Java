package com.example.haibazotest.controller;

import com.example.haibazotest.domain.dto.req.user.LoginReqDTO;
import com.example.haibazotest.domain.dto.req.user.RegisterReqDTO;
import com.example.haibazotest.domain.dto.res.user.UserResDTO;
import com.example.haibazotest.service.IUserService;
import com.example.haibazotest.until.ResponseData;
import com.example.haibazotest.until.SuccessCode;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auths")
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class AuthRestController {
    IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseData<?>> register(@RequestBody RegisterReqDTO registerReqDTO) {
        return ResponseEntity.ok(
                ResponseData.builder()
                        .code(SuccessCode.CREATED.getCode())
                        .message(SuccessCode.CREATED.getMessage())
                        .data(userService.register(registerReqDTO))
                        .build()
        );
    }

    @GetMapping("/login")
    public ResponseEntity<ResponseData<?>> login(@RequestBody LoginReqDTO loginReqDTO) {
        return ResponseEntity.ok(
                ResponseData.builder()
                        .code(SuccessCode.LOGIN.getCode())
                        .message(SuccessCode.LOGIN.getMessage())
                        .data(userService.login(loginReqDTO))
                        .build()
        );
    }
}
