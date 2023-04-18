package com.cg.onlineplantnursery.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.onlineplantnursery.Entity.Plant;
import com.cg.onlineplantnursery.Service.PlantServiceImpl;

@RestController
@RequestMapping("/plant")
public class PlantController {
	@Autowired
	private PlantServiceImpl plantService;

	@PostMapping("/add")
	public Plant saveOrder(@RequestBody Plant plant) {
		return plantService.addPlant(plant);
	}

	@PutMapping("/update")
	public Plant updatePlant(@RequestBody Plant plant) {
		return plantService.updatePlant(plant);
	}
	@DeleteMapping("/delete/{deleteId}")
	public void deletePlant(@PathVariable Integer deleteId) {
		plantService.deletePlant(deleteId);
	}

	@GetMapping("/view")
	public List<Plant> fetchAllOrders() {
		return plantService.viewAllPlants();
	}

	@GetMapping("/view/{id}")
	public Plant fetchById(@PathVariable("id") int plantId) {
		return plantService.viewPlant(plantId);
	}

	@GetMapping("/view/{name}")
	public Plant fetchByName(@PathVariable("name") String commonName) {
		return plantService.viewPlantByName(commonName);
	}
	@GetMapping("/view/{type}")
	public Plant fetchByType(@PathVariable("name") String plantType) {
		return (Plant) plantService.viewAllPlantsByType(plantType);
	}

}
