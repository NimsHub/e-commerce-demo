package com.nimshub.softwarearchitecturedemo.order;

import java.util.List;

import com.nimshub.softwarearchitecturedemo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
	
	public List<OrderDetail> findByUser(User user);

}
