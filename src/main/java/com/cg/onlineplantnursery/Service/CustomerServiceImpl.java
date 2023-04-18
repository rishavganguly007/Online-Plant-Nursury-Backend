package com.cg.onlineplantnursery.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.onlineplantnursery.Entity.Address;
import com.cg.onlineplantnursery.Entity.Customer;
import com.cg.onlineplantnursery.Exception.CustomerNotFoundException;
import com.cg.onlineplantnursery.Repository.ICustomerRepository;

@Service
public class CustomerServiceImpl implements ICustomerService{
	@Autowired
	private ICustomerRepository response;
	
	@Override
	public List<Customer> viewAllCustomers() {
		if(response.findAll() == null)
			throw new CustomerNotFoundException("No Customer found");
		return response.findAll();
	}
	
	@Override
	public Customer addCustomer(Customer customer) {
		return response.save(customer);
	}
	
	@Override
	public Customer updateCustomer(Customer customer) {
		return response.save(customer);
	}
	
	@Override
	public Customer deleteCustomer(Integer customerId) {
		Optional<Customer> obj = response.findById(customerId);
		if(obj.get() == null)
			throw new CustomerNotFoundException("Customer not created");
		response.deleteById(customerId);
		return obj.get();
	}	
	
	@Override
	public boolean validateCustomer(String username, String password) {
		Optional<Customer> customer = response.findByUsernameAndPassword(username, password);
		if(customer.get() == null)
			return false;
		else
			return true;
	}

	@Override
	public Customer viewCustomer(Integer customerId) {
		Optional<Customer> customer = response.findById(customerId);
		if(customer.get() == null)
			throw new CustomerNotFoundException("Customer not created");
		return customer.get();
	}
	
	public Optional<Customer> viewByUserName(String username, String password) {
		Optional<Customer> customer = response.findByUsernameAndPassword(username, password);
		if(customer.get() == null)
			throw new CustomerNotFoundException("Customer not created");
		return customer;		
	}

	@Override
	public List<Object[]> viewOrders(Integer customerId) {
		List<Object[]> obj = response.findOrders(customerId);
		return obj;
	}

	@Override
	public Customer updateAddress(Integer customerId, Address address) {
		Customer customer = response.findById(customerId).get();
		if(customer == null)
			throw new CustomerNotFoundException("Customer not created");
		customer.setAddress(address);
		return response.save(customer);
	}

}
