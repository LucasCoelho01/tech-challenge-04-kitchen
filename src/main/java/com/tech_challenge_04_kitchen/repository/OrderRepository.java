package com.tech_challenge_04_kitchen.repository;

import com.tech_challenge_04_kitchen.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
}