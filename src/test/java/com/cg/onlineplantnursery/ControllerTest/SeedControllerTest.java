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
import com.cg.onlineplantnursery.Controller.SeedController;
import com.cg.onlineplantnursery.Entity.Seed;
import com.cg.onlineplantnursery.Service.SeedServiceImpl;

@WebMvcTest(SeedController.class)
public class SeedControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private SeedServiceImpl seedService;

	private Seed seed;

	@BeforeEach
	void setup() {
		@SuppressWarnings("unused")
		Seed seed = Seed.builder().commonName("herb")
				.bloomTime("summer").watering("less")
				.difficultyLevel("high")
				.temparature("moderate")
				.typeOfSeeds("flowering")
				.seedsDescription("Small plants")
				.seedsCost(45.00)
				.seedsPerPacket(20).build();

	}

	@Test(expected=NullPointerException.class)
	public void updateSeed() throws Exception {

		Seed ipseed = Seed.builder().commonName("herb")
				.bloomTime("summer").watering("less")
				.difficultyLevel("high")
				.temparature("moderate")
				.typeOfSeeds("flowering")
				.seedsDescription("Small plants")
				.seedsCost(45.00)
				.seedsPerPacket(20).build();

		Mockito.when(seedService.updateSeed(ipseed)).thenReturn(seed);

		mvc.perform(MockMvcRequestBuilders.put("/update")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\n"+"}"))
		.andExpect(MockMvcResultMatchers
				.status().isOk());
	}
	
	@Test(expected=NullPointerException.class)
	public void fetchSeedById() throws Exception {
		Mockito.when(seedService.viewSeed(0))
		.thenReturn(seed);

		mvc.perform(MockMvcRequestBuilders.get("/viewId/{seedId}")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.commonName")
				.value(seed.getCommonName()));
	}	

}
