package com.nimshub.softwarearchitecturedemo.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Nirmala : 08:September:2023
 * This controller implements all end points required to handle the requests of Cart
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/Carts")
public class CartController {

    private final CartService cartService;
    @PutMapping("add")
    public ResponseEntity<String> addToCart(@RequestBody CartUpdateRequest request){
        cartService.addToCart(request);
        return new ResponseEntity<>("Successfully added to the cart", HttpStatus.OK);
    }

    @PutMapping("remove")
    public ResponseEntity<String> removeFromCart(@RequestBody CartUpdateRequest request){
        cartService.removeFromCart(request);
        return new ResponseEntity<>("Successfully removed from the cart", HttpStatus.OK);
    }
}