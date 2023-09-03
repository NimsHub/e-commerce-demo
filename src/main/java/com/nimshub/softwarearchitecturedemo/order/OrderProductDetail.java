package com.nimshub.softwarearchitecturedemo.order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderProductDetail {
    private Integer productId;
    private Integer quantity;
}
