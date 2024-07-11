package com.tech_challenge_04_kitchen.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    private String id;
    private String customer;
    private List<String> products;
    private String status;
    private String timestamp;
    private BigDecimal totalPrice;

    public Order() {}
}
