package com.nimshub.softwarearchitecturedemo.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @Author Nirmala : 03:September:2023
 * This controller implements all end points required to handle the requests of Order
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderDetails(@PathVariable UUID orderId) {
        Order order = orderService.getOrderDetails(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
        return new ResponseEntity<>("order has been placed", HttpStatus.OK);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<String> cancelOrder(@PathVariable UUID orderId) {
        orderService.cancelOrder(orderId);
        return new ResponseEntity<>("order has been canceled", HttpStatus.OK);
    }
}