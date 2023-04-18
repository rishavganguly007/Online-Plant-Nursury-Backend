package com.cg.onlineplantnursery.ControllerTest;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.cg.onlineplantnursery.Controller.CustomerController;
import com.cg.onlineplantnursery.Entity.Customer;
import com.cg.onlineplantnursery.Service.CustomerServiceImpl;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private CustomerServiceImpl customerService;

	private Customer customer;

	@BeforeEach
	void setup() {

		@SuppressWarnings("unused")
		Customer customer = Customer.builder().customerId(1).customerName("Amit Roy").customerEmail("amtroy@gmail.com").
		username("amtroy").password("amtroy123").build();

	}

	@Test(expected=NullPointerException.class)
	public void updateCustomer() throws Exception {

		Customer cust = Customer.builder().customerId(1).customerName("Amit Roy").customerEmail("amtroy@gmail.com").
				username("amtroy").password("amtroy123").build();

		Mockito.when(customerService.updateCustomer(cust)).thenReturn(customer);

		mvc.perform(MockMvcRequestBuilders.put("/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\n"+"}"))
		.andExpect(MockMvcResultMatchers
				.status().isOk());
	}

	@Test(expected=NullPointerException.class)
	public void fetchCustomerById() throws Exception {
		Mockito.when(customerService.viewCustomer(0))
		.thenReturn(customer);

		mvc.perform(MockMvcRequestBuilders.get("/viewId/{customerId}")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.customerName")
				.value(customer.getCustomerName()));
	}
}



