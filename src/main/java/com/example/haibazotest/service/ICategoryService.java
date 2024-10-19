package com.example.haibazotest.service;

import com.example.haibazotest.domain.dto.req.CategoryReqDTO;
import com.example.haibazotest.domain.dto.res.CategoryResDTO;

import java.util.List;

public interface ICategoryService {
    List<CategoryResDTO> getAllCategory();

    CategoryResDTO getCategoryById(Long id);
    CategoryResDTO addCategory(CategoryReqDTO categoryReqDTO);
    CategoryResDTO updateCategoryById(Long id, CategoryReqDTO categoryReqDTO);
    void deleteCategoryById(Long id);
}
