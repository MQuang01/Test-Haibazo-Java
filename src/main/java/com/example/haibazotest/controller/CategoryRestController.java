package com.example.haibazotest.controller;

import com.example.haibazotest.domain.dto.req.CategoryReqDTO;
import com.example.haibazotest.service.ICategoryService;
import com.example.haibazotest.until.ResponseData;
import com.example.haibazotest.until.SuccessCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryRestController {
    ICategoryService categoryService;
    @GetMapping
    public ResponseEntity<ResponseData<?>> getCategories() {
        return ResponseEntity.ok(
                ResponseData.builder()
                        .code(SuccessCode.GET_SUCCESSFUL.getCode())
                        .message(SuccessCode.GET_SUCCESSFUL.getMessage())
                        .data(categoryService.getAllCategory())
                        .build()
        );
    }
    @PostMapping
    public ResponseEntity<ResponseData<?>> createCategory(@RequestBody CategoryReqDTO categoryReqDTO) {
        return ResponseEntity.ok(
                ResponseData.builder()
                        .code(SuccessCode.CREATED.getCode())
                        .message(SuccessCode.CREATED.getMessage())
                        .data(categoryService.addCategory(categoryReqDTO))
                        .build()
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<?>> getCategoryById(@PathVariable String id) {
        return ResponseEntity.ok(
                ResponseData.builder()
                        .code(SuccessCode.GET_SUCCESSFUL.getCode())
                        .message(SuccessCode.GET_SUCCESSFUL.getMessage())
                        .data(categoryService.getCategoryById(Long.valueOf(id)))
                        .build()
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<?>> deleteCategoryById(@PathVariable String id) {
        categoryService.deleteCategoryById(Long.valueOf(id));
        return ResponseEntity.ok(
                ResponseData.builder()
                        .code(SuccessCode.DELETE_SUCCESSFUL.getCode())
                        .message(SuccessCode.DELETE_SUCCESSFUL.getMessage())
                        .build()
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<ResponseData<?>> updateCategoryById(@PathVariable String id, @RequestBody CategoryReqDTO categoryReqDTO) {
        return ResponseEntity.ok(
                ResponseData.builder()
                        .code(SuccessCode.UPDATE_SUCCESSFUL.getCode())
                        .message(SuccessCode.UPDATE_SUCCESSFUL.getMessage())
                        .data(categoryService.updateCategoryById(Long.valueOf(id), categoryReqDTO))
                        .build()
        );
    }
}
