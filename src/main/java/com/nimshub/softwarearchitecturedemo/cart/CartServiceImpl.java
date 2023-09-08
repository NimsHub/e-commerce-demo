package com.nimshub.softwarearchitecturedemo.cart;

import com.nimshub.softwarearchitecturedemo.exceptions.ResourceNotFoundException;
import com.nimshub.softwarearchitecturedemo.product.Product;
import com.nimshub.softwarearchitecturedemo.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Nirmala : 31:August:2023
 * This service implements all methods required to handle the business logic of Cart
 */
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Override
    public void addToCart(CartUpdateRequest request) {

        Cart cart = cartRepository.findByUserId(request.getUserId())
                .orElse(Cart.builder().userId(request.getUserId()).build());

        List<Product> productList = cart.getProductList();
        Product product = productRepository.findByProductId(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        productList.add(product);
        cart.setProductList(productList);
    }

    @Override
    public void removeFromCart(CartUpdateRequest request) {
        Cart cart = cartRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found for user [%s]"
                        .formatted(request.getUserId())));

        List<Product> productList = cart.getProductList();
        Product product = productRepository.findByProductId(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        productList.remove(product);
        cart.setProductList(productList);
    }
}