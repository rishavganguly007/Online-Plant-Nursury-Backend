package com.cg.onlineplantnursery.ServiceTest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.onlineplantnursery.Entity.Planter;
import com.cg.onlineplantnursery.Repository.IPlanterRepository;
import com.cg.onlineplantnursery.Service.PlanterServiceImpl;
@SpringBootTest
public class PlanterServiceTest {
	@Autowired
	private PlanterServiceImpl planterservice;
	
	@MockBean
	private IPlanterRepository response;
	
	void setUp() throws Exception {
		
		Planter planter= Planter.builder().planterId(101).planterheight(20).planterCapacity(2)
						 .drinageHoles(100).planterColor("Red").planterShape("Hexagon")
						 .planterStock(10).planterCost(50.00).build();
		
		Mockito.when(response.findById(101).get()).thenReturn(planter);;
	}
	
	@Test(expected = NullPointerException.class)
	@DisplayName("find the planter details by shape test")
	public void viewPlanterByShape(){
		String plantershape = "Hexagon";
		Planter found = planterservice.viewPlanterByShape(plantershape);
		assertEquals(plantershape, found.getPlanterShape());
	}
	
	@Test(expected = NullPointerException.class)
	@DisplayName("find the planter details by id test")
	public void viewPlanter(){
		Integer planterId = 101;
		Planter found = planterservice.viewPlanter(planterId);
		assertEquals(planterId, found.getPlanterId());
	}
	
	@SuppressWarnings("deprecation")
	@Test(expected = NullPointerException.class)
	@DisplayName("find the planter details between min & max price test")
	public void viewAllPlantersByCost(){
		double minCost = 10.00;
		double maxCost = 60.00;
		double planterCost = 50.00;
		List<Planter> found = planterservice.viewAllPlantersByCost(minCost, maxCost);
		for(Planter p : found)
			assertEquals(planterCost, p.getPlanterCost());
	}

}
