package com.cg.onlineplantnursery.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cg.onlineplantnursery.Entity.Planter;

@Repository
public interface IPlanterRepository extends JpaRepository<Planter, Integer>{

	Planter findByPlanterShape(String planterShape);
	
	List<Planter> findByPlanterCostBetween(double minCost, double maxCost);

}
