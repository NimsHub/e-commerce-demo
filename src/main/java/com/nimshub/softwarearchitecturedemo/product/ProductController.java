package com.nimshub.softwarearchitecturedemo.product;

import com.nimshub.softwarearchitecturedemo.product.dto.ProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;

    @GetMapping("{id}")
    public ResponseEntity<Product> getProduct(@PathVariable UUID id) {
        Product product = productService.getProduct(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority(ADMIN)")
    @PostMapping("")
    public ResponseEntity<String> createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
        return new ResponseEntity<>("new product has been created", HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority(ADMIN)")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>("product has been deleted successfully", HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority(ADMIN)")
    @PutMapping("{id}")
    public ResponseEntity<String> updateProduct(@RequestBody ProductRequest productRequest, @PathVariable UUID id) {
        productService.updateProduct(productRequest, id);
        return new ResponseEntity<>("product has been updated", HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<List<Product>> searchProduct(@RequestParam String searchTerm) {
        List<Product> searchedProducts = productService.searchProducts(searchTerm);
        return new ResponseEntity<>(searchedProducts, HttpStatus.OK);
    }
}
