package com.nimshub.softwarearchitecturedemo.product;

import com.nimshub.softwarearchitecturedemo.product.dto.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public Product getProduct(UUID id) {
        return productRepository.findByProductId(id).orElseThrow(() ->
                new RuntimeException("Product with id : [%s] not found".formatted(id)));
    }

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .productId(UUID.randomUUID())
                .description(productRequest.getDescription())
                .image(productRequest.getImage())
                .price(productRequest.getPrice())
                .isAvailable(productRequest.isAvailable())
                .build();
        productRepository.save(product);
    }

    public void updateProduct(ProductRequest productRequest, UUID productId) {

        Product product = getProduct(productId);

        product.setAvailable(productRequest.isAvailable());
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setImage(productRequest.getImage());

        productRepository.save(product);
    }
}
