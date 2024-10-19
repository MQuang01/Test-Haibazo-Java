package com.example.haibazotest.mapper;

import com.example.haibazotest.domain.Category;
import com.example.haibazotest.domain.dto.req.CategoryReqDTO;
import com.example.haibazotest.domain.dto.res.CategoryResDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryResDTO toCategoryResDTO(Category category);

    Category toCategory(CategoryReqDTO categoryReqDTO);
}
