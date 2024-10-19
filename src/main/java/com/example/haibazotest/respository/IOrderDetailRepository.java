package com.example.haibazotest.respository;

import com.example.haibazotest.domain.Order;
import com.example.haibazotest.domain.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findByOrderId(Long id);
}