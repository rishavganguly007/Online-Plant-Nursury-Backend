package com.cg.onlineplantnursery.Controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.onlineplantnursery.Entity.Address;
import com.cg.onlineplantnursery.Entity.Customer;
import com.cg.onlineplantnursery.Entity.Orders;
import com.cg.onlineplantnursery.Exception.OrderNotFoundException;
import com.cg.onlineplantnursery.Service.CustomerServiceImpl;
import com.cg.onlineplantnursery.Service.OrderServiceImpl;
import com.cg.onlineplantnursery.Service.PlantServiceImpl;
import com.cg.onlineplantnursery.Service.PlanterServiceImpl;
import com.cg.onlineplantnursery.Service.SeedServiceImpl;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	private int validUser = 0;
	private Integer validId = 0;
	private String welcome = "Welcome \n........................\n Customer Id : ";
	@Autowired
	private CustomerServiceImpl service;
	
	@Autowired
	private OrderServiceImpl orderservice;
	
	@Autowired
	private PlantServiceImpl plantservice;
	
	@Autowired
	private SeedServiceImpl seedservice;
	
	@Autowired
	private PlanterServiceImpl planterservice;
	
	@PostMapping("/customer")
	public ResponseEntity<?> addCustomer(@Valid @RequestBody Customer customer) {
		return ResponseEntity.ok(service.addCustomer(customer));
	}
	
	@PostMapping(value = "/order", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveOrder(@RequestBody Orders order) {
		if(validUser == 1) {
			Customer customer = service.viewCustomer(validId);
			order.setCustomer(customer);
			return ResponseEntity.ok(orderservice.addOrder(order));
		}else
			return ResponseEntity.ok("Not Logged In");
	}
	
	@DeleteMapping("/customer")
	public ResponseEntity<?> deleteCustomer() {
		if(validUser == 1) {
			return ResponseEntity.ok(service.deleteCustomer(validId));
		}else
			return ResponseEntity.ok("Not Logged In");
		
	}
	
	@GetMapping("/validate/{username}/{password}")
	public ResponseEntity<Boolean> validUser(@PathVariable("username") String username, @PathVariable("password") String password) {
		boolean value = service.validateCustomer(username, password);			
		return ResponseEntity.ok(value);	
	}
	
	@GetMapping("/login/{username}/{password}")
	public ResponseEntity<?> validateUser(@PathVariable("username") String username, @PathVariable("password") String password) {
		boolean value = service.validateCustomer(username, password);		
		if (value == true) {
			validUser = 1;
			Customer customer = service.viewByUserName(username, password).get();
			validId = customer.getCustomerId();
			
//			return ResponseEntity.ok(welcome + validId + 
//					                 "\n Name : " + customer.getCustomerName() + 
//					                 "\n Email : " + customer.getCustomerEmail() + 
//					                 "\n.........................\n Address --- \n House no : " 
//					                  + customer.getAddress().getHouseNo() + 
//					                 "\n Colony : " + customer.getAddress().getColony() +
//					                 "\n City : " +	customer.getAddress().getCity() + 
//					                 "\n State : " + customer.getAddress().getState() +
//					                 "\n Pincode : " +	customer.getAddress().getPincode());
			return ResponseEntity.ok(customer);

		}
		else
			return ResponseEntity.ok("Invalid Credentials");		
	}
	
	
	@GetMapping("/order/{id}")
	public ResponseEntity<?> viewOrder(@PathVariable("id") Integer orderId) throws OrderNotFoundException {
		if(validUser == 1) {
			Orders order =  orderservice.viewOrder(orderId);
			return ResponseEntity.ok("..........................Order Details......................... \n " +
			                         "Booking id : " + order.getBookingOrderId() + "\n" +
			                         "Order date : " + order.getOrderDate() + "\n" +
			                         "Transaction mode : " + order.getTransactionMode() + "\n" +
			                         "Quantity ordered : " + order.getQuantity() + "\n" +
			                         "Total Cost : " + order.getTotalCost()); 
		}else
			return ResponseEntity.ok("Not Logged In");
		
	}
	@GetMapping("/lastOrder")
	public ResponseEntity<?> fetchLastOrder(){
		if(validUser == 1) {
			Customer customer = service.viewCustomer(validId);
			int len = customer.getOrders().size();
		    Orders order = customer.getOrders().get(len-1);
		    return ResponseEntity.ok(order); 
		}else
			return ResponseEntity.ok("Not Logged In");
	}
	
	@GetMapping("/orders")
	public ResponseEntity<?> fetchOrders(){
		if(validUser == 1) {
			Customer customer = service.viewCustomer(validId);		
		    List<Orders> order = customer.getOrders();
		    List<String> orderlist = new ArrayList<>();
		    int i = 1;
		    for(Orders obj : order) {
		    	orderlist.add( "Serial : " + i +
		    			 	   ", Booking id : " + obj.getBookingOrderId() + 
                               ", Order date : " + obj.getOrderDate() + 
                               ", Transaction mode : " + obj.getTransactionMode() + 
                               ", Quantity ordered : " + obj.getQuantity() + 
                               ", Total Cost : " + obj.getTotalCost());
		    	i++;
		    }
		    return ResponseEntity.ok(orderlist); 
		}else
			return ResponseEntity.ok("Not Logged In");
	}
	
	@PatchMapping(path = "/customer")
	public ResponseEntity<?> updateCustomerById(@RequestBody Map<Object, Object> fields) {
	    if(validUser == 1)
	    {
	      Customer customer = service.viewCustomer(validId);
	      if(customer != null) {
	    	  fields.forEach((key,value) -> {
	    		  Field field = ReflectionUtils.findField(Customer.class, (String)key);
	    		  field.setAccessible(true);
	    		  ReflectionUtils.setField(field, customer, value);
	    	  });	    	  
	      }return ResponseEntity.ok(service.updateCustomer(customer));
	      
	    }else
			return ResponseEntity.ok("Not Logged In");
	}
	
	@PutMapping("/address")
	public ResponseEntity<?> updateAddressById(@RequestBody Address address){
		if(validUser == 1) {
			return ResponseEntity.ok(service.updateAddress(validId, address));			
		}else
			return ResponseEntity.ok("Not Logged In");
	}
	
	@GetMapping("/logout")
	public ResponseEntity<?> logout(){
		if(validUser == 1) {
			validUser = 0;
			return ResponseEntity.ok("Logged out...");		
		}else
			return ResponseEntity.ok("Not Logged In");
	}
	
	/*.........................Plant.................................*/
	@GetMapping("/plant")
	public ResponseEntity<?> fetchAllOrders() {
		if(validUser == 1) {
			return ResponseEntity.ok(plantservice.viewAllPlants());
		}else
		return ResponseEntity.ok("Not Logged In");
	}
	
	/*........................Planter................................*/
	@GetMapping("/planter")
	public ResponseEntity<?> viewAllPlanters() {
		if(validUser == 1) {
			return ResponseEntity.ok(planterservice.viewAllPlanters());
		}else
		return ResponseEntity.ok("Not Logged In");
	}
	
	/*.......................Seed...................................*/
	@GetMapping("/seed")
	public ResponseEntity<?> viewAllSeeds() {
		if(validUser == 1) {			
			return ResponseEntity.ok(seedservice.viewAllSeeds());
		}else
		return ResponseEntity.ok("Not Logged In");
	}
	
}

