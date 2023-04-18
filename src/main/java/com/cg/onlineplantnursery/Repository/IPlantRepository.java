package com.cg.onlineplantnursery.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.onlineplantnursery.Entity.Plant;

@Repository
public interface IPlantRepository extends JpaRepository<Plant, Integer>{
	
	Plant findByCommonNameIgnoreCase(String commonName);

	List<Plant> findByTypeOfPlantIgnoreCase(String typeOfPlant);

}
