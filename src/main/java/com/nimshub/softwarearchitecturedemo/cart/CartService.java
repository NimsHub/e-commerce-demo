package com.nimshub.softwarearchitecturedemo.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Author Nirmala : 31:August:2023
 * This service implements all methods required to handle the business logic of Cart
 */
@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
}