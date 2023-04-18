package com.cg.onlineplantnursery.Service;

import java.util.List;
import com.cg.onlineplantnursery.Entity.Orders;

public interface IOrderService {
	
	Orders addOrder(Orders order);
	
	Orders updateOrder(Orders order);
	
	void deleteOrder(Integer orderId);
	
	Orders viewOrder(Integer orderId);
	
	List<Orders> viewAllOrders();
}

