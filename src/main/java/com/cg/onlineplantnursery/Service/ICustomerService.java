package com.cg.onlineplantnursery.Service;

import java.util.List;
import com.cg.onlineplantnursery.Entity.Address;
import com.cg.onlineplantnursery.Entity.Customer;

public interface ICustomerService {
	
	Customer addCustomer(Customer customer);
	
	Customer updateCustomer(Customer tenant);

	Customer deleteCustomer(Integer customerId);

	Customer viewCustomer(Integer customerId);	

	List<Customer> viewAllCustomers();

	boolean validateCustomer(String userName, String password);
	
	List<Object[]> viewOrders(Integer customerId);
	
	Customer updateAddress(Integer customerId, Address address);

}

