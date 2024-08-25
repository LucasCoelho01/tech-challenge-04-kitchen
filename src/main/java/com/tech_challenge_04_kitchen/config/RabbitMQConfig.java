package com.tech_challenge_04_kitchen.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String QUEUE_KITCHEN_REQUEST = "kitchenRequest";
    public static final String QUEUE_KITCHEN_RESPONSE = "kitchenResponse";
    public static final String QUEUE_KITCHEN_UPDATE = "kitchenUpdate";


    @Bean
    public Queue queue_kitchenRequest() { return new Queue(QUEUE_KITCHEN_REQUEST, true); }

    @Bean
    public Queue queue_KitchenResponse() { return new Queue(QUEUE_KITCHEN_RESPONSE, true); }

    @Bean
    public Queue queue_KitchenUpdate() { return new Queue(QUEUE_KITCHEN_UPDATE, true); }
}
