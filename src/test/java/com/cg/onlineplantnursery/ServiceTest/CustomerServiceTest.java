package com.cg.onlineplantnursery.ServiceTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.onlineplantnursery.Entity.Customer;
import com.cg.onlineplantnursery.Repository.ICustomerRepository;
import com.cg.onlineplantnursery.Service.CustomerServiceImpl;

@SpringBootTest
public class CustomerServiceTest {
	@Autowired
	private CustomerServiceImpl customerService;

	@MockBean
	private ICustomerRepository response;

	@BeforeEach
	void setUp() throws Exception {

		Customer customer = Customer.builder().customerId(101).customerName("Mj Mohan").customerEmail("mj.1998.kumar@gmail.com")
							.username("mohan.MJ").password("password").build();

		Mockito.when(response.findById(1).get()).thenReturn(customer);
	}

	@Test(expected = NullPointerException.class)
	@DisplayName("find Customer details by id test")
	public void viewCustomer() {
		Integer customerId = 101;
		Customer found = customerService.viewCustomer(customerId);
		assertEquals(customerId, found.getCustomerId());
	}

	@Test(expected = NullPointerException.class)
	@DisplayName("find the Customer details by Name test")
	public void viewCustomerByName() {
		String username = "mohan.MJ";
		String password = "password";
		Customer found = customerService.viewByUserName(username, password).get();
		assertEquals(username, found.getUsername());
		assertEquals(password, found.getPassword());
	}

}
