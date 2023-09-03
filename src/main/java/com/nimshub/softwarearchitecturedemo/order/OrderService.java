package com.nimshub.softwarearchitecturedemo.order;

import com.nimshub.softwarearchitecturedemo.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Author Nirmala : 31:August:2023
 * This service implements all methods required to handle the business logic of Order
 */
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .orderId(UUID.randomUUID())
                .userEmail(orderRequest.getUserEmail())
                .isCompleted(false)
                .productList(orderRequest.getOrderProductDetails().toString())
                .isCanceled(false)
                .build();

        orderRepository.save(order);
    }

    public Order getOrderDetails(UUID orderId) {
        return orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("order with order ID : [%s] not found"
                        .formatted(orderId)));
    }

    public void cancelOrder(UUID orderId) {
        Order order = orderRepository.findByOrderId(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("order with order ID : [%s] not found"
                        .formatted(orderId)));
        order.setCanceled(true);
        orderRepository.save(order);
    }
}