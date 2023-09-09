package com.nimshub.softwarearchitecturedemo.order;

import java.util.ArrayList;
import java.util.List;

import com.nimshub.softwarearchitecturedemo.cart.Cart;
import com.nimshub.softwarearchitecturedemo.configuration.JwtRequestFilter;
import com.nimshub.softwarearchitecturedemo.cart.CartRepository;
import com.nimshub.softwarearchitecturedemo.product.Product;
import com.nimshub.softwarearchitecturedemo.product.ProductRepository;
import com.nimshub.softwarearchitecturedemo.user.User;
import com.nimshub.softwarearchitecturedemo.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailService {
	
	private static final String ORDER_PLACED = "Placed";  
	
	private final OrderDetailRepository orderDetailRepository;
	
	private final ProductRepository productRepository;
	
	private final UserRepository userRepository;
	
	private final CartRepository cartRepository;
	
	public List<OrderDetail> getAllOrderDetails(){
		List<OrderDetail> orderDetails = new ArrayList<>();
		orderDetailRepository.findAll().forEach(e -> orderDetails.add(e));
		
		return orderDetails;
	}
	
	public List<OrderDetail> getOrderDetails() {
		String currentUser = JwtRequestFilter.CURRENT_USER;
		User user = userRepository.findById(currentUser).get();
		
		return orderDetailRepository.findByUser(user);
	}
	
	public void placeOrder(OrderInput orderInput, boolean isSingleProductCheckout) {
		List<OrderProductQuantity> productQuantityList = orderInput.getOrderProductQuantityList();
		
		for(OrderProductQuantity o: productQuantityList) {
			Product product = productRepository.findById(o.getProductId()).get();
			
			String currentUser = JwtRequestFilter.CURRENT_USER;
			User user= userRepository.findById(currentUser).get();
			
			OrderDetail orderDetail = new OrderDetail(
					orderInput.getFullName(),
					orderInput.getFullAddress(),
					orderInput.getContactNumber(),
					orderInput.getAlternateContactNumber(),
					ORDER_PLACED,
					product.getProductDiscountedPrice()*o.getQuantity(),
					product,
					user);
			
			if(!isSingleProductCheckout) {
				List<Cart> carts= cartRepository.findByUser(user);
				carts.stream().forEach(x -> cartRepository.deleteById(x.getCartId()));
				
			}
			orderDetailRepository.save(orderDetail);
		}
	}
	
	

}
