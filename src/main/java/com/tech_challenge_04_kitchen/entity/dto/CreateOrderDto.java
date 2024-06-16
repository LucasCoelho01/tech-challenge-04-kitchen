package com.tech_challenge_04_kitchen.entity.dto;

import java.math.BigDecimal;
import java.util.List;

public record CreateOrderDto(
        String id,
        String customer,
        List<String> products,
        String status,
        String timestamp,
        BigDecimal totalPrice
) { }
