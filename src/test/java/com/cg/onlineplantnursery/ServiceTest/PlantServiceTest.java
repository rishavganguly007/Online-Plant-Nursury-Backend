package com.cg.onlineplantnursery.ServiceTest;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.cg.onlineplantnursery.Entity.Plant;
import com.cg.onlineplantnursery.Repository.IPlantRepository;
import com.cg.onlineplantnursery.Service.PlantServiceImpl;

@SpringBootTest
public class PlantServiceTest {
	
	@Autowired
	private PlantServiceImpl plantservice;
	
	@MockBean
	private IPlantRepository response;
	
	void setUp() throws Exception {
		
		Plant plant = Plant.builder().plantId(101).plantHeight(135).plantSpread("Wide")
				      .commonName("Mahogany").bloomTime("Summer").medicinalOrCulinaryUse("medical")
				      .difficultyLevel("Easy").temparature("30").typeOfPlant("Tree")
				      .plantDescription("Description").plantsStock(100).plantCost(100.00).build();
		
		Mockito.when(response.findByCommonNameIgnoreCase("mahogany")).thenReturn(plant);
	}
	
	@Test(expected = NullPointerException.class)
	@DisplayName("find the plant details by name test")
	public void viewPlantByName(){
		String plantName = "Mahogany";
		Plant found = plantservice.viewPlantByName(plantName);
		assertEquals(plantName, found.getCommonName());
	}
	
	@Test(expected = NullPointerException.class)
	@DisplayName("find the plant details by id test")
	public void viewPlant(){
		Integer plantId = 101;
		Plant found = plantservice.viewPlant(plantId);
		assertEquals(plantId, found.getPlantId());
	}
	
	@Test(expected = NullPointerException.class)
	@DisplayName("find the plant details by id test")
	public void viewAllPlantsByType(){
		String type = "Tree";
		List<Plant> found = plantservice.viewAllPlantsByType(type);
		for(Plant p : found)
			assertEquals(type, p.getTypeOfPlant());
	}

}

