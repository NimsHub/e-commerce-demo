package com.nimshub.softwarearchitecturedemo.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @Author Nirmala : 31:August:2023
 * This entity class defines the all the properties of Order
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_details")
public class Order {
    @Id
    @SequenceGenerator(name = "ORDER_SEQ", sequenceName = "ORDER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ORDER_SEQ")
    private Integer id;
    private UUID orderId;
    private String productList;
    private String userEmail;
    private boolean isCompleted;
    private boolean isCanceled;
}