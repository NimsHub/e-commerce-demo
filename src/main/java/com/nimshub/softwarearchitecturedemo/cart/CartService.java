package com.nimshub.softwarearchitecturedemo.cart;

public interface CartService {
    void addToCart(CartUpdateRequest request);

    void removeFromCart(CartUpdateRequest request);
}
