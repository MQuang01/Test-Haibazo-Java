package com.example.haibazotest.service.imp;

import com.example.haibazotest.domain.Category;
import com.example.haibazotest.domain.Product;
import com.example.haibazotest.domain.dto.req.ProductReqDTO;
import com.example.haibazotest.domain.dto.res.ProductResDTO;
import com.example.haibazotest.mapper.ProductMapper;
import com.example.haibazotest.respository.ICategoryRepository;
import com.example.haibazotest.respository.IProductRepository;
import com.example.haibazotest.service.IProductService;
import com.example.haibazotest.until.ErrorCode;
import com.example.haibazotest.until.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ProductService implements IProductService {
    IProductRepository productRepository;
    ProductMapper productMapper = ProductMapper.INSTANCE;
    ICategoryRepository categoryRepository;

    @Override
    public List<ProductResDTO> getAllProduct() {
        return productRepository.findAll().stream().map(productMapper::toProductResDTO).toList();
    }

    @Override
    public ProductResDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException(ErrorCode.ID_NOT_FOUND.getMessage()));
        return productMapper.toProductResDTO(product);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductResDTO updateProductById(Long id, ProductReqDTO productReqDTO) {
        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new NoSuchElementException(ErrorCode.ID_NOT_FOUND.getMessage()));

        ObjectMapper.mapNonNullFields(productReqDTO, existingProduct);
        if (productReqDTO.getCategoryId() != null) {
            Category category = categoryRepository.findById(Long.valueOf(productReqDTO.getCategoryId()))
                    .orElseThrow(() -> new NoSuchElementException(ErrorCode.ID_NOT_FOUND.getMessage()));
            existingProduct.setCategory(category);
        }
        return productMapper.toProductResDTO(productRepository.save(existingProduct));
    }

    @Override
    public ProductResDTO addProduct(ProductReqDTO productReqDTO) {
        Product product = productMapper.toProduct(productReqDTO);
        product.setCategory(categoryRepository.findById(Long.valueOf(productReqDTO.getCategoryId())).orElseThrow(() -> new NoSuchElementException(ErrorCode.ID_NOT_FOUND.getMessage())));
        return productMapper.toProductResDTO(productRepository.save(product));
    }
}
