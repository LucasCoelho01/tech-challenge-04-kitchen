package com.tech_challenge_04_kitchen.controller;

import com.tech_challenge_04_kitchen.entity.Order;
import com.tech_challenge_04_kitchen.entity.dto.CreateOrderDto;
import com.tech_challenge_04_kitchen.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kitchen")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    ResponseEntity<Order> createOrder(@RequestBody CreateOrderDto createOrderDto) {
        return new ResponseEntity<>(orderService.createOrder(createOrderDto), HttpStatus.CREATED);
    }

    @GetMapping
    ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    ResponseEntity<Optional<Order>> getOrderById(@PathVariable String id) {
        Optional<Order> order = orderService.getOrderById(id);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
