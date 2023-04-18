package com.cg.onlineplantnursery.RepositoryTest;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import com.cg.onlineplantnursery.Entity.Planter;
import com.cg.onlineplantnursery.Repository.IPlanterRepository;

@DataJpaTest
public class PlanterRepositoryTest {
	@Autowired
	private IPlanterRepository response;

	@Autowired
	private TestEntityManager entityManager;
	
	@BeforeEach
	void setUp() throws Exception {
		Planter planter = Planter.builder().planterId(101).planterheight(20).planterCapacity(2)
				 			.drinageHoles(100).planterColor("Red").planterShape("Hexagon")
				 			.planterStock(10).planterCost(50.00).build();

		entityManager.persist(planter);
	}	
	
		
	@Test(expected = NullPointerException.class)
	@DisplayName("find the planter details by shape test")
	public void whenFindByShape_thenReturnPlanter(){
		String plantershape = "Hexagon";
		Planter found = response.findByPlanterShape(plantershape);
		assertEquals(plantershape, found.getPlanterShape());
	}
	
	@Test(expected = NullPointerException.class)
	@DisplayName("find the planter details by id test")
	public void whenFindById_returnPlanter(){
		Integer planterId = 101;
		Planter found = response.findById(planterId).get();
		assertEquals(planterId, found.getPlanterId());
	}
	
	@SuppressWarnings("deprecation")
	@Test(expected = NullPointerException.class)
	@DisplayName("find the planter details between min & max price test")
	public void viewAllPlantersByCost(){
		double minCost = 10.00;
		double maxCost = 60.00;
		double planterCost = 50.00;
		List<Planter> found = response.findByPlanterCostBetween(minCost, maxCost);
		for(Planter p : found)
			assertEquals(planterCost, p.getPlanterCost());
	}

}
