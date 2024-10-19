package com.example.haibazotest.mapper;

import com.example.haibazotest.domain.User;
import com.example.haibazotest.domain.dto.req.user.RegisterReqDTO;
import com.example.haibazotest.domain.dto.res.user.UserResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(RegisterReqDTO registerReqDTO);
    UserResDTO toUserResDTO(User user);
}
