package com.nimshub.softwarearchitecturedemo.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * @Author Nirmala : 31:August:2023
 * This interface implements all methods required database transactions for Order using Jpa
 */

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    Optional<Order> findByOrderId(UUID orderId);
}