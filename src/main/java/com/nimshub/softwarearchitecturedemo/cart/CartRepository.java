package com.nimshub.softwarearchitecturedemo.cart;

import java.util.List;

import com.nimshub.softwarearchitecturedemo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
	
	public List<Cart> findByUser(User user);

}
