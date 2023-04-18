package com.cg.onlineplantnursery.RepositoryTest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.cg.onlineplantnursery.Entity.Plant;
import com.cg.onlineplantnursery.Repository.IPlantRepository;

@DataJpaTest
public class PlantRepositoryTest {
	@Autowired
	private IPlantRepository response;

	@Autowired
	private TestEntityManager entityManager;
	
	@BeforeEach
	void setUp() throws Exception {
		Plant plant = Plant.builder().plantId(101).plantHeight(135).plantSpread("Wide")
			      		.commonName("Mahogany").bloomTime("Summer").medicinalOrCulinaryUse("medical")
			      		.difficultyLevel("Easy").temparature("30").typeOfPlant("Tree")
			      		.plantDescription("Description").plantsStock(100).plantCost(100.00).build();

		entityManager.persist(plant);
	}	
	
	@Test(expected = NullPointerException.class)
	@DisplayName("find the plant details by name test")
	public void whenFindByName_thenReturnPlant(){
		String plantName = "Mahogany";
		Plant found = response.findByCommonNameIgnoreCase(plantName);
		assertEquals(plantName, found.getCommonName());
	}
	
	@Test(expected = NullPointerException.class)
	@DisplayName("find the plant details by id test")
	public void whenFindById_thenReturnPlant(){
		Integer plantId = 101;
		Plant found = response.findById(plantId).get();
		assertEquals(plantId, found.getPlantId());
	}
	
	@Test(expected = NullPointerException.class)
	@DisplayName("find the plant details by id test")
	public void whenFindByType_thenReturnPlant(){
		String type = "Tree";
		List<Plant> found = response.findByTypeOfPlantIgnoreCase(type);
		for(Plant p : found)
			assertEquals(type, p.getTypeOfPlant());
	}

}
