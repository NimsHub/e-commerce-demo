package com.nimshub.softwarearchitecturedemo.product;

import com.nimshub.softwarearchitecturedemo.product.dto.ProductRequest;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product getProduct(UUID id);
    void createProduct(ProductRequest productRequest);
    void deleteProduct(UUID productId);
    void updateProduct(ProductRequest productRequest,UUID productId);
    List<Product> searchProducts(String query);

}
