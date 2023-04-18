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

import com.cg.onlineplantnursery.Controller.PlanterController;
import com.cg.onlineplantnursery.Entity.Planter;
import com.cg.onlineplantnursery.Service.PlanterServiceImpl;

@WebMvcTest(PlanterController.class)
public class PlanterControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private PlanterServiceImpl planterService;
	
	private Planter planter;
	
	@BeforeEach
	void setup() {
		
		@SuppressWarnings("unused")
		Planter planter = Planter.builder().planterId(1).planterheight(10).planterCapacity(2).drinageHoles(100)
				.planterColor("Red").planterShape("Low").planterCost(50.00).build();
		
	}
	
	@Test(expected=NullPointerException.class)
	public void updatePlanter() throws Exception {

		Planter p = Planter.builder().planterId(1).planterheight(10).planterCapacity(2).drinageHoles(100)
				.planterColor("Red").planterShape("Low").planterCost(50.00).build();
		
		Mockito.when(planterService.updatePlanter(p)).thenReturn(planter);
		
		mvc.perform(MockMvcRequestBuilders.put("/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\n"+"}"))
				.andExpect(MockMvcResultMatchers
						.status().isOk());
	}
	
	@Test(expected=NullPointerException.class)
	public void fetchPlanterById() throws Exception {
		Mockito.when(planterService.viewPlanter(1))
		.thenReturn(planter);
		
	mvc.perform(MockMvcRequestBuilders.get("/viewId/{planterId}")
			.contentType(MediaType.APPLICATION_JSON))
	.andExpect(MockMvcResultMatchers.status().isOk())
	.andExpect(MockMvcResultMatchers.jsonPath("$.planterColor")
			.value(planter.getPlanterColor()));
	}

}

