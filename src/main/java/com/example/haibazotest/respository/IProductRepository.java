package com.example.haibazotest.respository;

import com.example.haibazotest.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
