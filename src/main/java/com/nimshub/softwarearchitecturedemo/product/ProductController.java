package com.nimshub.softwarearchitecturedemo.product;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product")
    public Product getProduct(@RequestParam Integer id) {
        return productService.getProduct(id.longValue());
    }

    @PostMapping("/product")
    public void createProduct(@RequestBody Product product){
        productService.createProduct(product);
    }
}
