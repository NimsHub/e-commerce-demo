package com.nimshub.softwarearchitecturedemo.order;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderRequest {
    private String name;
    private String address;
    private String contact;
    private String userEmail;
    private List<OrderProductDetail> orderProductDetails;
}
