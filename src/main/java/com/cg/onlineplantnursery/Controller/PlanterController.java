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

import com.cg.onlineplantnursery.Entity.Planter;
import com.cg.onlineplantnursery.Service.PlanterServiceImpl;

@RestController
@RequestMapping("/planter")
public class PlanterController {
	@Autowired
	private PlanterServiceImpl service;
	
	@PostMapping("/add")
	public Planter addPlanter(@RequestBody Planter planter) {
		return service.addPlanter(planter);
	}

	@GetMapping("/view")
	public List<Planter> viewAllPlanters() {
		return service.viewAllPlanters();
	}
	
	@GetMapping("/view/{id}")
	public Planter viewById(@PathVariable("id") int planterId) {
		return service.viewPlanter(planterId);
	}
	
	@GetMapping("/view/{minCost, maxCost}")
	public List<Planter> viewAllPlanters(@PathVariable("minCost, maxCost") double minCost, double maxCost) {
		return service.viewAllPlantersByCost(minCost, maxCost);
	}
	
	@GetMapping("/view/{planterShape}")
	public Planter viewById(@PathVariable("planterShape") String planterShape) {
		return service.viewPlanterByShape(planterShape);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable("id") Integer planterId) {
		service.deletePlanter(planterId);
	}

	@PutMapping("/update")
	public Planter updatePlanter(@RequestBody Planter planter) {
		return service.updatePlanter(planter);
	}

}

