package com.cg.onlineplantnursery.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.onlineplantnursery.Entity.Plant;
import com.cg.onlineplantnursery.Exception.PlantNotFoundException;
import com.cg.onlineplantnursery.Repository.IPlantRepository;

@Service
public class PlantServiceImpl implements IPlantService{
	@Autowired
	private IPlantRepository response;

	public Plant addPlant(Plant plant) {
		return response.save(plant);
	}

	@Override
	public Plant updatePlant(Plant plant) {
		return response.save(plant);
	}

	@Override
	public Plant deletePlant(Integer plantId) {
		Optional<Plant> plant = response.findById(plantId);
		if(plant == null)
			throw new PlantNotFoundException("No plant found with plant Id: " + plantId);
		response.deleteById(plantId);
		return plant.get();
	}

	@Override
	public Plant viewPlant(int plantId) {
		Optional<Plant> plant = response.findById(plantId);
		if (!plant.isPresent()) {
			throw new PlantNotFoundException("No plant found with plant Id: " + plantId);
		} else {
			return plant.get();
		}
	}

	@Override
	public Plant viewPlantByName(String commonName) {
		Plant plant = response.findByCommonNameIgnoreCase(commonName);
		if (plant == null) 
			throw new PlantNotFoundException("No plant found with plant name: " + commonName);
		else 
			return response.findByCommonNameIgnoreCase(commonName);
	}

	@Override
	public List<Plant> viewAllPlants() {
		List<Plant> plant =  response.findAll();
		if(plant == null)
			throw new PlantNotFoundException("No plants available.");
		return plant;
	}

	@Override
	public List<Plant> viewAllPlantsByType(String typeOfPlant) {
		List<Plant> plant = response.findByTypeOfPlantIgnoreCase(typeOfPlant);
		if (plant == null) {
			throw new PlantNotFoundException("Plant Type is not available");
		} else {
			return plant;
		}
	}
}

