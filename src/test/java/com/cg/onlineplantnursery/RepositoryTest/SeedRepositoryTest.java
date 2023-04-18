package com.cg.onlineplantnursery.RepositoryTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import com.cg.onlineplantnursery.Entity.Seed;
import com.cg.onlineplantnursery.Repository.ISeedRepository;

@DataJpaTest
public class SeedRepositoryTest {
	@Autowired
	private ISeedRepository response;

	@Autowired
	private TestEntityManager entityManager;

	@BeforeEach
	void setUp() throws Exception {
		Seed seed = Seed.builder().seedId(101).commonName("Cashewnuts").bloomTime("Winter")
					.watering("Sparse").difficultyLevel("Moderate").seedsDescription("Produces dry friut")
					.temparature("Cold").typeOfSeeds("Fruit producing").seedsCost(300.00)
					.seedsPerPacket(20).build();

		entityManager.persist(seed);
	}

	@Test(expected = NullPointerException.class)
	public void whenFindById_thenReturnSeed() {
		Integer seedId = 101;
		Seed seed = response.findById(seedId).get();
		assertEquals(seed.getCommonName(), "Cashewnuts");
	}
	
	@Test(expected = NullPointerException.class)
	@DisplayName("find the seed details by name test")
	public void whenFindByName_thenReturnSeed(){
		String seedName = "Cashewnuts";
		Seed found = response.findByCommonNameIgnoreCase(seedName).get();
		assertEquals(seedName, found.getCommonName());
	}

}
