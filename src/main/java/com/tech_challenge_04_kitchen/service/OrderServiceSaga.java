package com.tech_challenge_04_kitchen.service;

import com.google.gson.Gson;
import com.tech_challenge_04_kitchen.config.RabbitMQConfig;
import com.tech_challenge_04_kitchen.entity.Order;
import com.tech_challenge_04_kitchen.entity.dto.CreateOrderDto;
import com.tech_challenge_04_kitchen.repository.OrderRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceSaga {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private OrderRepository orderRepository;

    @RabbitListener(queues = RabbitMQConfig.QUEUE_KITCHEN_REQUEST)
    public void handleKitchenMessage(String jsonRequest) {
        System.out.println("Recebido queue QUEUE_KITCHEN_REQUEST");
        Gson gson = new Gson();
        CreateOrderDto createOrderDto = gson.fromJson(jsonRequest, CreateOrderDto.class);

        System.out.println("Pedido: " + createOrderDto.id() + " recebido");

        var order = new Order();

        order.setId(createOrderDto.id());
        order.setCustomer(createOrderDto.customer());
        order.setStatus("Pedido Recebido");
        order.setProducts(createOrderDto.products());
        order.setTimestamp(createOrderDto.timestamp());
        order.setTotalPrice(createOrderDto.totalPrice());

        orderRepository.save(order);
        System.out.println("Pedido salvo");

        String json = gson.toJson(order);

        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_KITCHEN_RESPONSE, json);
        System.out.println("Enviado queue QUEUE_KITCHEN_RESPONSE");
    }
}
