package com.nimshub.softwarearchitecturedemo.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    public Product getProduct(Long id){
        return productRepository.findById(id).orElseThrow(()->
                new RuntimeException("Product with id : [%s] not found".formatted(id)));
    }
    public void createProduct(Product product){
        productRepository.save(product);
    }
}
