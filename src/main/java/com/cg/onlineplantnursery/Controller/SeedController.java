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
import com.cg.onlineplantnursery.Entity.Seed;
import com.cg.onlineplantnursery.Service.SeedServiceImpl;

@RestController
@RequestMapping("/seed")
public class SeedController {
	
	@Autowired
	private SeedServiceImpl service;
	
	@GetMapping("/view")
	public List<Seed> viewAllSeeds() {
		return service.viewAllSeeds();
	}

	@GetMapping("/view/{typeOfSeed}")
	public List<Seed> viewAllSeeds(@PathVariable String typeOfSeed) {
		return service.viewAllSeeds(typeOfSeed);
	}

	@GetMapping("/view/{seedId}")
	public Seed viewSeed(@PathVariable Integer seedId) {
		return service.viewSeed(seedId);
	}

	@GetMapping("/view/{commonName}")
	public Seed viewSeed(@PathVariable String commonName) {
		return service.viewSeedByName(commonName);
	}

	@PostMapping("/add")
	public Seed addSeed(@RequestBody Seed seed) {
		return service.addSeed(seed);
	}

	@PutMapping("/update")
	public Seed updateSeed(@RequestBody Seed seed) {
		return service.updateSeed(seed);
	}

	@DeleteMapping("/delete/{seedId}")
	public void deleteSeed(@PathVariable Integer seedId) {
		service.deleteSeed(seedId);
	}

}
