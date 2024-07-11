package com.tech_challenge_04_kitchen.service;

import com.tech_challenge_04_kitchen.entity.Order;
import com.tech_challenge_04_kitchen.entity.dto.CreateOrderDto;
import com.tech_challenge_04_kitchen.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public Order createOrder(CreateOrderDto createOrderDto) {
        var order = new Order();

        order.setId(createOrderDto.id());
        order.setCustomer(createOrderDto.customer());
        order.setStatus("Recebido");
        order.setProducts(createOrderDto.products());
        order.setTimestamp(createOrderDto.timestamp());
        order.setTotalPrice(createOrderDto.totalPrice());

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(String id) {
        return orderRepository.findById(id);
    }

    public Optional<Order> updateOrderStatus(String id, String status) {
        Optional<Order> order = getOrderById(id);

        order.get().setStatus(status);

        return order;
    }
}
