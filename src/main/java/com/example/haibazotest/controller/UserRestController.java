package com.example.haibazotest.controller;

import com.example.haibazotest.domain.dto.req.user.RegisterReqDTO;
import com.example.haibazotest.service.IUserService;
import com.example.haibazotest.until.ErrorCode;
import com.example.haibazotest.until.ResponseData;
import com.example.haibazotest.until.SuccessCode;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class UserRestController {
    IUserService userService;

    @GetMapping
    public ResponseEntity<ResponseData<?>> getAllUser() {
        return ResponseEntity.ok(
                ResponseData.builder()
                        .code(SuccessCode.GET_SUCCESSFUL.getCode())
                        .message(SuccessCode.GET_SUCCESSFUL.getMessage())
                        .data(userService.getAllUser())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<?>> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(
                ResponseData.builder()
                        .code(SuccessCode.GET_SUCCESSFUL.getCode())
                        .message(SuccessCode.GET_SUCCESSFUL.getMessage())
                        .data(userService.getUserById(Long.valueOf(id)))
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<?>> deleteUserById(@PathVariable String id) {
        userService.deleteUserById(Long.valueOf(id));
        return ResponseEntity.ok(
            ResponseData.builder()
                    .code(SuccessCode.DELETE_SUCCESSFUL.getCode())
                    .message(SuccessCode.DELETE_SUCCESSFUL.getMessage())
                    .build()
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<?>> updateUserById(@PathVariable String id, @RequestBody RegisterReqDTO registerReqDTO) {
        return ResponseEntity.ok(
                ResponseData.builder()
                        .code(SuccessCode.UPDATE_SUCCESSFUL.getCode())
                        .message(SuccessCode.UPDATE_SUCCESSFUL.getMessage())
                        .data(userService.updateUserById(Long.valueOf(id), registerReqDTO))
                        .build()
        );
    }
}
