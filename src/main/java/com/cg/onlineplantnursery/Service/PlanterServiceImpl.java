package com.cg.onlineplantnursery.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.onlineplantnursery.Entity.Planter;
import com.cg.onlineplantnursery.Exception.PlanterNotFoundException;
import com.cg.onlineplantnursery.Repository.IPlanterRepository;

@Service
public class PlanterServiceImpl implements IPlanterService{
	
	@Autowired
	private IPlanterRepository planterrepo;

	@Override
	public Planter addPlanter(Planter planter) {
		return planterrepo.save(planter);
	}

	@Override
	public Planter updatePlanter(Planter planter) {
		return planterrepo.save(planter);
	}

	@Override
	public Planter deletePlanter(Integer planterId) {
		Planter planter = planterrepo.findById(planterId).get();
		if(planter == null)
			throw new PlanterNotFoundException("No planter available with planter Id: " + planterId);
		planterrepo.deleteById(planterId);
		return planter;
	}

	@Override
	public Planter viewPlanter(Integer planterId) {
		Optional<Planter> planter = planterrepo.findById(planterId);
		if(planter == null)
			throw new PlanterNotFoundException("No planter available with planter Id: " + planterId);
		return planter.get();
	}
     
	@Override
	public Planter viewPlanterByShape(String planterShape) {
		Planter planter = planterrepo.findByPlanterShape(planterShape);
		if(planter == null)
			throw new PlanterNotFoundException("No planter available with planter shape: " + planterShape);
		return planterrepo.findByPlanterShape(planterShape);
	}
    
	@Override
	public List<Planter> viewAllPlanters() {
		List<Planter> planters = planterrepo.findAll();
		if(planters == null)
			throw new PlanterNotFoundException("No planters available.");
		return planterrepo.findAll();
	}

	@Override
	public List<Planter> viewAllPlantersByCost(double minCost, double maxCost) {
		List<Planter> planters = planterrepo.findByPlanterCostBetween(minCost, maxCost);
		if(planters == null)
			throw new PlanterNotFoundException("No planters available between Rs. " + minCost +
					                           " and Rs. " + maxCost);
		return planters;
	}

	@Override
	public double viewPlantCost(Integer planterId) {
		Planter planter = planterrepo.findById(planterId).get();
		if(planter == null)
			throw new PlanterNotFoundException("No planters available.");
		double price = planter.getPlanterCost();
		return price;
	}

}

