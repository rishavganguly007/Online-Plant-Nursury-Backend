package com.cg.onlineplantnursery.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.onlineplantnursery.Entity.Orders;

@Repository
public interface IOrderRepository extends JpaRepository<Orders, Integer>{
	public Orders findById(int orderId);
}