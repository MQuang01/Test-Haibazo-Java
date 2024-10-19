package com.example.haibazotest.service;

import com.example.haibazotest.domain.dto.req.user.LoginReqDTO;
import com.example.haibazotest.domain.dto.req.user.RegisterReqDTO;
import com.example.haibazotest.domain.dto.res.user.UserResDTO;

import java.util.List;

public interface IUserService {
    UserResDTO login(LoginReqDTO loginReqDTO);
    UserResDTO register(RegisterReqDTO registerReqDTO);
    List<UserResDTO> getAllUser();
    UserResDTO getUserById(Long id);
    void deleteUserById(Long id);
    UserResDTO updateUserById(Long id, RegisterReqDTO registerReqDTO);
}
