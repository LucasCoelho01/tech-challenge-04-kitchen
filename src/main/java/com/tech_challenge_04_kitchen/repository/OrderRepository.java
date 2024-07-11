package com.tech_challenge_04_kitchen.repository;

import com.tech_challenge_04_kitchen.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
}