package com.cg.onlineplantnursery.ServiceTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.cg.onlineplantnursery.Entity.Seed;
import com.cg.onlineplantnursery.Repository.ISeedRepository;
import com.cg.onlineplantnursery.Service.SeedServiceImpl;

@SpringBootTest
public class SeedServiceTest {
	@Autowired
	private SeedServiceImpl seedService;
	
	@MockBean
	private ISeedRepository response;
	
	@BeforeEach
	void setUp() throws Exception {
		
		Seed seed = Seed.builder().seedId(101).commonName("Cashewnuts").bloomTime("Winter").watering("Sparse")
					.difficultyLevel("Moderate").seedsDescription("Produces dry friut").temparature("Cold")
					.typeOfSeeds("Fruit producing").seedsStock(40).seedsCost(300.00).seedsPerPacket(20).build();
		
		Mockito.when(response.findByCommonNameIgnoreCase("Cashewnuts").get()).thenReturn(seed);
	}
	
	@Test(expected = NullPointerException.class)
	@DisplayName("find the seed details by name test")
	public void viewSeedByName(){
		String seedName = "Cashewnuts";
		Seed found = seedService.viewSeedByName(seedName);
		assertEquals(seedName, found.getCommonName());
	}
	
	@Test(expected = NullPointerException.class)
	@DisplayName("find the seed details by id test")
	public void viewSeed(){
		Integer seedId = 101;
		Seed found = seedService.viewSeed(seedId);
		assertEquals(seedId, found.getSeedId());
	}
	
}
