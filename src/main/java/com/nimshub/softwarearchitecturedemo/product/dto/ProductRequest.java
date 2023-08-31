package com.nimshub.softwarearchitecturedemo.product.dto;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String price;
    private boolean isAvailable;
    private String description;
    private String image;
}
