package com.tech_challenge_04_kitchen.service;

import com.tech_challenge_04_kitchen.entity.Order;
import com.tech_challenge_04_kitchen.entity.dto.CreateOrderDto;
import com.tech_challenge_04_kitchen.entity.dto.UpdateOrderStatusDto;
import com.tech_challenge_04_kitchen.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderServiceSaga orderServiceSaga;

    public Order createOrder(CreateOrderDto createOrderDto) {
        var order = new Order();

        order.setId(createOrderDto.id());
        order.setCustomer(createOrderDto.customer());
        order.setStatus("Pedido Recebido");
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

    public Optional<Order> updateOrderStatus(String id, UpdateOrderStatusDto updateOrderStatusDto) {
        Optional<Order> orderFound = getOrderById(id);

        if (orderFound.isPresent()) {
            orderFound.get().setStatus(updateOrderStatusDto.status());
            Order order = new Order();

            order.setId(orderFound.get().getId());
            order.setCustomer(orderFound.get().getCustomer());
            order.setStatus(updateOrderStatusDto.status());
            order.setTimestamp(orderFound.get().getTimestamp());
            order.setProducts(orderFound.get().getProducts());
            order.setTotalPrice(orderFound.get().getTotalPrice());

            orderRepository.save(order);
            orderServiceSaga.sendUpdateOrder(order);
        }

        return orderFound;
    }
}
