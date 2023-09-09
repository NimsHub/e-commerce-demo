package com.nimshub.softwarearchitecturedemo.dao;

import java.util.List;

import com.nimshub.softwarearchitecturedemo.entity.OrderDetail;
import com.nimshub.softwarearchitecturedemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface OrderDetailDao extends JpaRepository<OrderDetail, Integer> {
	
	public List<OrderDetail> findByUser(User user);

}
