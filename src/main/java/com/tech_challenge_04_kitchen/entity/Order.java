package com.tech_challenge_04_kitchen.entity;

import com.tech_challenge_04_kitchen.entity.dto.CreateOrderDto;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Data
@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private String customer;
    private List<String> products;
    private String status;
    private String timestamp;
    private BigDecimal totalPrice;

/*    public Order(CreateOrderDto createOrderDto) {
        this.id = createOrderDto.id();
        this.customer = createOrderDto.customer();
        this.products = createOrderDto.products();
        this.status = "Recebido";
        this.timestamp = createOrderDto.timestamp();
        this.totalPrice = createOrderDto.totalPrice();
    }*/
}
