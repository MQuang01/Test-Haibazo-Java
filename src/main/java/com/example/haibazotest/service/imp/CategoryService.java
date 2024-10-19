package com.example.haibazotest.service.imp;

import com.example.haibazotest.domain.Category;
import com.example.haibazotest.domain.dto.req.CategoryReqDTO;
import com.example.haibazotest.domain.dto.res.CategoryResDTO;
import com.example.haibazotest.mapper.CategoryMapper;
import com.example.haibazotest.respository.ICategoryRepository;
import com.example.haibazotest.service.ICategoryService;
import com.example.haibazotest.until.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService implements ICategoryService {
    ICategoryRepository categoryRepository;
    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Override
    public List<CategoryResDTO> getAllCategory() {
        return categoryRepository.findAll().stream().map(categoryMapper::toCategoryResDTO).toList();
    }

    @Override
    public CategoryResDTO getCategoryById(Long id) {
        return categoryRepository.findById(id).map(categoryMapper::toCategoryResDTO).orElse(null);
    }

    @Override
    public CategoryResDTO addCategory(CategoryReqDTO categoryReqDTO) {
        Category category = categoryMapper.toCategory(categoryReqDTO);
        return categoryMapper.toCategoryResDTO(categoryRepository.save(category));
    }

    @Override
    public CategoryResDTO updateCategoryById(Long id, CategoryReqDTO categoryReqDTO) {
        Category existingCategory = categoryRepository.findById(id).orElseThrow(() -> new NoSuchElementException(ErrorCode.ID_NOT_FOUND.getMessage()));
        existingCategory.setName(categoryReqDTO.getName());
        return categoryMapper.toCategoryResDTO(categoryRepository.save(existingCategory));
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
}
