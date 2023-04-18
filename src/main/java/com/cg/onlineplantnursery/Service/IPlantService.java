package com.cg.onlineplantnursery.Service;

import java.util.List;
import com.cg.onlineplantnursery.Entity.Plant;

public interface IPlantService {
	
	Plant addPlant(Plant plant);

	Plant updatePlant(Plant plant);

	Plant viewPlant(int plantId);

	Plant viewPlantByName(String commonName);

	List<Plant> viewAllPlants();

	List<Plant> viewAllPlantsByType(String typeOfPlant);

	Plant deletePlant(Integer plantId);

}
