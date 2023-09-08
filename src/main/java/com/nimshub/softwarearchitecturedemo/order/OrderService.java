package com.nimshub.softwarearchitecturedemo.order;


import java.util.UUID;

public interface OrderService {
     void placeOrder(OrderRequest orderRequest);
     Order getOrderDetails(UUID orderId);
     void cancelOrder(UUID orderId);
}
