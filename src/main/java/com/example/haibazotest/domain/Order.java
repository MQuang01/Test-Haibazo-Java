package com.example.haibazotest.domain;

import com.example.haibazotest.domain.enumeration.EStatusOrder;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    User user;
    String description;
    String address;
    String phone;
    String email;
    LocalDateTime createdDate;
    @Enumerated(EnumType.STRING)
    EStatusOrder statusOrder;
    double total;
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    List<OrderDetail> listOrderDetail;
}
