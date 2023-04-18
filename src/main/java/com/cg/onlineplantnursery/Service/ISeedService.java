package com.cg.onlineplantnursery.Service;

import java.util.List;
import com.cg.onlineplantnursery.Entity.Seed;

public interface ISeedService {
	
	Seed addSeed(Seed seed);

	Seed updateSeed(Seed seed);

	Seed deleteSeed(Integer seedId);

	Seed viewSeed(Integer seedId);

	Seed viewSeedByName(String commonName);

	List<Seed> viewAllSeeds();

	List<Seed> viewAllSeeds(String typeOfSeed);
	
}
