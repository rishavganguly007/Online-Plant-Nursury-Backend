package com.cg.onlineplantnursery.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.onlineplantnursery.Entity.Orders;
import com.cg.onlineplantnursery.Exception.OrderNotFoundException;
import com.cg.onlineplantnursery.Service.OrderServiceImpl;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderServiceImpl service;
	
	@PostMapping("/add")
	public Orders saveOrder(@RequestBody Orders order) {
		return service.addOrder(order);
	}

	@GetMapping("/view")
	public List<Orders> fetchAllOrders() {
		return service.viewAllOrders();
	}
    
	@GetMapping("/view/{id}")
	public Orders viewOrder(@PathVariable("id") Integer co_fk) throws OrderNotFoundException {
		return service.viewOrder(co_fk);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable("id") Integer orderId) {
		service.deleteOrder(orderId);
	}

	@PutMapping("/update")
	public Orders updateOrder(@RequestBody Orders order) {
		return service.updateOrder(order);
	}

}

