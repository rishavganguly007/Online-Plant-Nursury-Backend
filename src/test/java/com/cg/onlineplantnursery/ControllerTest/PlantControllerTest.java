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
import com.cg.onlineplantnursery.Controller.PlantController;
import com.cg.onlineplantnursery.Entity.Plant;
import com.cg.onlineplantnursery.Service.PlantServiceImpl;

@WebMvcTest(PlantController.class)
public class PlantControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private PlantServiceImpl plantService;

	private Plant plant;

	@BeforeEach
	void setup() {
		@SuppressWarnings("unused")
		Plant plant = Plant.builder().plantId(101).plantHeight(34).commonName("Cashewnuts").bloomTime("Winter")
				.medicinalOrCulinaryUse("Medicinal").difficultyLevel("Moderate").temparature("Cold")
				.typeOfPlant("Fruit Producing").plantDescription(null).plantCost(300.00).build();

	}

	@Test(expected = NullPointerException.class)
	public void updatePlant() throws Exception {
		Plant ipplant = Plant.builder().plantId(101).plantHeight(0).commonName("Cashewnuts").bloomTime("Winter")
				.medicinalOrCulinaryUse("Medicinal").difficultyLevel("Moderate").temparature("Cold")
				.typeOfPlant("Fruit Producing").plantDescription(null).plantCost(300.00).build();
		
		Mockito.when(plantService.updatePlant(ipplant)).thenReturn(plant);
		
		mvc.perform(MockMvcRequestBuilders.put("/updatePlant").contentType(MediaType.APPLICATION_JSON).content("{\n" + "}"))
				.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	@Test(expected = NullPointerException.class)
	public void fetchSeedById() throws Exception {
		Mockito.when(plantService.viewPlant(0)).thenReturn(plant);

		mvc.perform(MockMvcRequestBuilders.get("/order/{id}").contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.commonName").value(plant.getCommonName()));
	}
}

