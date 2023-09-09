package com.nimshub.softwarearchitecturedemo.dao;

import java.util.List;

import com.nimshub.softwarearchitecturedemo.entity.Cart;
import com.nimshub.softwarearchitecturedemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartDao extends JpaRepository<Cart, Integer> {
	
	public List<Cart> findByUser(User user);

}
