package com.cg.onlineplantnursery.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.onlineplantnursery.Entity.Customer;
import com.cg.onlineplantnursery.Entity.Orders;
import com.cg.onlineplantnursery.Entity.Plant;
import com.cg.onlineplantnursery.Entity.Planter;
import com.cg.onlineplantnursery.Entity.Seed;
import com.cg.onlineplantnursery.Exception.CustomerNotFoundException;
import com.cg.onlineplantnursery.Exception.OrderNotFoundException;
import com.cg.onlineplantnursery.Exception.PlantNotFoundException;
import com.cg.onlineplantnursery.Exception.PlanterNotFoundException;
import com.cg.onlineplantnursery.Exception.SeedNotFoundException;
import com.cg.onlineplantnursery.Repository.ICustomerRepository;
import com.cg.onlineplantnursery.Repository.IOrderRepository;
import com.cg.onlineplantnursery.Repository.IPlantRepository;
import com.cg.onlineplantnursery.Repository.IPlanterRepository;
import com.cg.onlineplantnursery.Repository.ISeedRepository;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService{
	@Autowired
	private IOrderRepository iorderrepository;
	
	@Autowired
	private ISeedRepository seedRepo;
	
	@Autowired
	private IPlantRepository plantRepo;
	
	@Autowired
	private IPlanterRepository planterRepo;
	
	@Autowired
	private ICustomerRepository customerRepo;
	
	@Override
	public Orders addOrder(Orders Myorder) {

			final Orders order = Myorder;
			Myorder.setOrderDate(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
			//......................................Seed..........................................................
			Double seedCost = 0.0;
			List<Seed> seed = new ArrayList<Seed>();
            int seedqty = 0;
            
			if (order.getSeed() != null) {
				for (Seed i : order.getSeed()) {
					seed.add(seedRepo.findById(i.getSeedId()).orElseThrow(
							() -> new SeedNotFoundException("Seed by id" + i.getSeedId() + " was not found")));
					seedqty = order.getSeedQuantity();
				}
				Myorder.setSeed(seed);
			//....................................Updating stock...................................................
				IntStream.range(0, seed.size()).forEachOrdered(i -> {
					if (seed.get(i).getSeedsStock() >= order.getSeedQuantity())
						seed.get(i).setSeedsStock(seed.get(i).getSeedsStock() - order.getSeedQuantity());     //add
					else
						throw new RuntimeException("Product Out of Stock");
				});

			//.................................calculating seed cost................................................
				seedCost = IntStream.range(0, order.getSeed().size())
					 .mapToDouble(i -> order.getSeed().get(i).getSeedsCost() * order.getSeedQuantity()).sum(); //add
			}
			
			
			//......................................Plant...........................................................
			Double plantCost = 0.0;
			List<Plant> plant = new ArrayList<Plant>();
			int plantqty = 0;
			
			if (order.getPlant() != null) {
				for (Plant i : order.getPlant()) {
					plant.add(plantRepo.findById(i.getPlantId()).orElseThrow(
							() -> new PlantNotFoundException("Plant by id" + i.getPlantId() + " was not found")));
					plantqty = order.getPlantQuantity();
				}
				Myorder.setPlant(plant);

			//.................................Upadting Stock.......................................................
				IntStream.range(0, plant.size()).forEachOrdered(i -> {
					if (plant.get(i).getPlantsStock() >= order.getPlantQuantity())
						plant.get(i).setPlantsStock(plant.get(i).getPlantsStock() - order.getPlantQuantity()); //add
					else
						throw new RuntimeException("Product Out of Stock");
				});

			//..............................calculating Plant cost.................................................
				plantCost = IntStream.range(0, order.getPlant().size())
				  .mapToDouble(i -> order.getPlant().get(i).getPlantCost() * order.getPlantQuantity()).sum();  //add
			}
								
			//.........................................Planter.....................................................
			Double planterCost = 0.0;
			List<Planter> planter = new ArrayList<Planter>();
			int planterqty = 0;

			if (order.getPlanters() != null) {
				for (Planter i : order.getPlanters()) {
					planter.add(planterRepo.findById(i.getPlanterId()).orElseThrow(
							  () -> new PlanterNotFoundException("Planter by id" + i.getPlanterId() + " was not found")));
					planterqty = order.getPlanterQuantity();
				}
				Myorder.setPlanters(planter);

			//.....................................Updating Stock...................................................
				IntStream.range(0, planter.size()).forEachOrdered(i -> {
					if (planter.get(i).getPlanterStock() >= order.getPlanterQuantity())
						planter.get(i).setPlanterStock(planter.get(i).getPlanterStock() - order.getPlanterQuantity());       //add
					else
						throw new RuntimeException("Product Out of Stock");
				});

			//.................................calculating Planter cost.............................................
				planterCost = IntStream.range(0, order.getPlanters().size())
				    .mapToDouble(i -> order.getPlanters().get(i).getPlanterCost() * order.getPlanterQuantity()).sum();   //add
			}

			Myorder.setTotalCost(plantCost + planterCost + seedCost);			
			Myorder.setQuantity(seedqty + plantqty + planterqty);
			
			//...........................................Customer..................................................
			Customer customer = customerRepo.findById(Myorder.getCustomer().getCustomerId()).orElseThrow(
					            () -> new CustomerNotFoundException("Customer Not Found"));
			Myorder.setCustomer(customer);
			
			
			Myorder =  iorderrepository.save(Myorder);
			seedRepo.saveAll(seed);
			plantRepo.saveAll(plant);
			planterRepo.saveAll(planter);
			customerRepo.save(customer);			
			return Myorder;
	}
	
	
	@Override
	public Orders updateOrder(Orders order) {
		return iorderrepository.save(order);
	}	
	
	@Override
	public List<Orders> viewAllOrders() {		
		return iorderrepository.findAll();
	}

	@Override
	public void deleteOrder(Integer orderId) {
		iorderrepository.deleteById(orderId);		
	}

	@Override
	public Orders viewOrder(Integer orderId) {
		Optional<Orders> order = iorderrepository.findById(orderId);
		if (!order.isPresent()) {
			throw new OrderNotFoundException("Order Id not found");
		}
		return order.get();
	}

}
