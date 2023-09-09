package com.nimshub.softwarearchitecturedemo.product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.nimshub.softwarearchitecturedemo.configuration.JwtRequestFilter;
import com.nimshub.softwarearchitecturedemo.cart.CartRepository;
import com.nimshub.softwarearchitecturedemo.user.UserRepository;
import com.nimshub.softwarearchitecturedemo.cart.Cart;
import com.nimshub.softwarearchitecturedemo.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	private final UserRepository userRepository;
	private final CartRepository cartRepository;
	
	public Product addNewProduct(Product product) {
		return productRepository.save(product);
	}
	
	public List<Product> getAllProducts(int pageNumber, String searchKey){
		Pageable pageable = PageRequest.of(pageNumber, 8);
		
		if(searchKey.equals("")) {
			return productRepository.findAll(pageable);
		}else {
			return productRepository.findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(searchKey, searchKey, pageable);
		}
		
	}
	
	public void deleteProductDetails(Integer productId) {
		productRepository.deleteById(productId);
	}

	public Product getProductDetailsById(Integer productId) {
		
		return productRepository.findById(productId).get();
	}
	
	public List<Product> getProductDetails(boolean isSingeProductCheckout, Integer productId) {
	
		if(isSingeProductCheckout && productId != 0) {
			List<Product> list= new ArrayList<>();
			Product product = productRepository.findById(productId).get();
			list.add(product);
			return list;
		}else {
		
			String username = JwtRequestFilter.CURRENT_USER;
			User user = userRepository.findById(username).get();
			List<Cart>  carts= cartRepository.findByUser(user);
			
			return carts.stream().map(x -> x.getProduct()).collect(Collectors.toList());
			
		}
		
	
	}
	
	
	

}
