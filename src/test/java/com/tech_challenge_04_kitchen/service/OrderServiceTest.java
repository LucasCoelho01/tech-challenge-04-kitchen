package com.tech_challenge_04_kitchen.service;

import com.tech_challenge_04_kitchen.entity.Order;
import com.tech_challenge_04_kitchen.entity.dto.CreateOrderDto;
import com.tech_challenge_04_kitchen.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private Order order;
    private CreateOrderDto createOrderDto;

    @BeforeEach
    void setUp() {
        createOrderDto = new CreateOrderDto("1", "testCustomer", List.of("product1", "product2"), "Recebido", "2023-01-01T10:00:00", new BigDecimal(100));

        order = new Order();
        order.setId("1");
        order.setCustomer("testCustomer");
        order.setStatus("Recebido");
        order.setProducts(List.of("product1", "product2"));
        order.setTimestamp("2023-01-01T10:00:00");
        order.setTotalPrice(BigDecimal.valueOf(200.00));
    }

    @Test
    void createOrder_success() {
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order createdOrder = orderService.createOrder(createOrderDto);

        assertNotNull(createdOrder);
        assertEquals("1", createdOrder.getId());
        assertEquals("testCustomer", createdOrder.getCustomer());
        assertEquals("Recebido", createdOrder.getStatus());
        assertEquals(List.of("product1", "product2"), createdOrder.getProducts());
        assertEquals("2023-01-01T10:00:00", createdOrder.getTimestamp());
        assertEquals(BigDecimal.valueOf(200.00), createdOrder.getTotalPrice());

        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void getAllOrders_success() {
        when(orderRepository.findAll()).thenReturn(List.of(order));

        List<Order> orders = orderService.getAllOrders();

        assertNotNull(orders);
        assertFalse(orders.isEmpty());
        assertEquals(1, orders.size());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    void getOrderById_success() {
        when(orderRepository.findById(anyString())).thenReturn(Optional.of(order));

        Optional<Order> foundOrder = orderService.getOrderById("1");

        assertTrue(foundOrder.isPresent());
        assertEquals(order, foundOrder.get());
        verify(orderRepository, times(1)).findById(anyString());
    }

    @Test
    void updateOrderStatus_success() {
        when(orderRepository.findById(anyString())).thenReturn(Optional.of(order));

        Optional<Order> updatedOrder = orderService.updateOrderStatus("1", "Preparando");

        assertTrue(updatedOrder.isPresent());
        assertEquals("Preparando", updatedOrder.get().getStatus());
    }
}
