package com.nimshub.softwarearchitecturedemo.cart;

import java.util.List;
import java.util.stream.Collectors;


import com.nimshub.softwarearchitecturedemo.configuration.JwtRequestFilter;
import com.nimshub.softwarearchitecturedemo.product.ProductRepository;
import com.nimshub.softwarearchitecturedemo.user.UserRepository;
import com.nimshub.softwarearchitecturedemo.product.Product;
import com.nimshub.softwarearchitecturedemo.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class CartService {
	
	private final CartRepository cartRepository;
	
	private final ProductRepository productRepository;
	
	private final UserRepository userRepository;
	
	public void deleteCartItem(Integer cartId) {
		cartRepository.deleteById(cartId);
	}
	
	public Cart addToCart(Integer productId) {
		
		Product product = productRepository.findById(productId).get();
		
		String username = JwtRequestFilter.CURRENT_USER;
		
		User user = null;
		
		if(username != null) {
			user = userRepository.findById(username).get();
			
		}
		
		List<Cart> cartList = cartRepository.findByUser(user);
		List<Cart> filteredList = cartList.stream().filter(x -> x.getProduct().getProductId() == productId).collect(Collectors.toList());
		
		if(filteredList.size() > 0) {
			return null;
		}
		
		
		if(product != null && user != null) {
			Cart cart = new Cart(product, user);
			return cartRepository.save(cart);
		}
		return null;
	}
	
	public List<Cart> getCartDetails(){
		String username = JwtRequestFilter.CURRENT_USER;
		User user = userRepository.findById(username).get();
		return cartRepository.findByUser(user);
		
	}
	
	

}
