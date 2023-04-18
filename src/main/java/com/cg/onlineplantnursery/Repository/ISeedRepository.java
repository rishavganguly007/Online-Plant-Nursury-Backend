package com.cg.onlineplantnursery.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.onlineplantnursery.Entity.Seed;

@Repository
public interface ISeedRepository extends JpaRepository<Seed, Integer>{
	
	Optional<Seed> findByCommonNameIgnoreCase(String commonName);

	List<Seed> findAllByTypeOfSeeds(String typeOfSeed);

}