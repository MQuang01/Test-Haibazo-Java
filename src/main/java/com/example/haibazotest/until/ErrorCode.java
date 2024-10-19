package com.example.haibazotest.until;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    USER_NOT_FOUND(404, "User Not Found", HttpStatus.NOT_FOUND),
    ID_NOT_FOUND(409, "Id Not Exists", HttpStatus.NOT_FOUND),
    ILLEGAL_STATE(400, "Wrong password or username", HttpStatus.BAD_REQUEST),
    ILLEGAL_ARGUMENT(409, "Email, Phone or Username Already Exists", HttpStatus.CONFLICT),
    USERNAME_EXISTS(409, "Username Exists", HttpStatus.CONFLICT),
    INVALID_PASSWORD(409, "Invalid Password", HttpStatus.CONFLICT),
    INVALID_INPUT(411, "Invalid Input", HttpStatus.CONFLICT);
    int code;
    String message;
    HttpStatus httpStatus;
    }
