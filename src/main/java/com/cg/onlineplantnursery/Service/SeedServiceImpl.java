package com.cg.onlineplantnursery.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.onlineplantnursery.Entity.Seed;
import com.cg.onlineplantnursery.Exception.SeedNotFoundException;
import com.cg.onlineplantnursery.Repository.ISeedRepository;

@Service
public class SeedServiceImpl implements ISeedService{

	@Autowired
	private ISeedRepository seedrepo ;
		
	@Override
	public List<Seed> viewAllSeeds() {		
		return seedrepo.findAll();
	}
	
	@Override
	public Seed viewSeed(Integer seedId)  {		
		return seedrepo.findById(seedId)
			   .orElseThrow(() -> new SeedNotFoundException("Seed by id: " + seedId + " was not found"));
	}	
	
	@Override
	public List<Seed> viewAllSeeds(String typeOfSeed) {		
		return seedrepo.findAllByTypeOfSeeds(typeOfSeed);
	}
	

	@Override
	public Seed viewSeedByName(String commonName) {
		return seedrepo.findByCommonNameIgnoreCase(commonName)				
			   .orElseThrow(() -> new SeedNotFoundException("Seed by common name: " + commonName + " was not found"));
	}
	
	@Override
	public Seed addSeed(Seed seed) {
		return seedrepo.save(seed);
	}

	@Override
	public Seed updateSeed(Seed seed) {
		return seedrepo.save(seed);
	}

	@Override
	public Seed deleteSeed(Integer seedId) {
		Seed seed = seedrepo.findById(seedId).get();
		if(seed == null)
			throw new SeedNotFoundException("Seed not created");
		seedrepo.deleteById(seedId);
		return seed;
	}

}
