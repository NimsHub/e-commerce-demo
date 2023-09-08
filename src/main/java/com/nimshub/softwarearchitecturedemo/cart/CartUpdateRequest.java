package com.nimshub.softwarearchitecturedemo.cart;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;
@Data
@Builder
public class CartUpdateRequest {
    private UUID userId;
    private UUID productId;
}
