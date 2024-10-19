package com.example.haibazotest.respository;

import com.example.haibazotest.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderRepository extends JpaRepository<Order, Long> {
  List<Order> findByUserId(Long id);
}