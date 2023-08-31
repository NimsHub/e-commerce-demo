package com.nimshub.softwarearchitecturedemo.product;

import com.nimshub.softwarearchitecturedemo.product.dto.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productServiceImpl;

    @GetMapping("/product")
    public Product getProduct(@RequestParam UUID id) {
        return productServiceImpl.getProduct(id);
    }

    @PostMapping("/product")
    public void createProduct(@RequestBody ProductRequest productRequest){
        productServiceImpl.createProduct(productRequest);
    }
}
