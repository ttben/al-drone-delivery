package fr.unice.polytech.si5.al.projet.truck;

import fr.unice.polytech.si5.al.projet.truck.domain.delivery.Delivery;
import fr.unice.polytech.si5.al.projet.truck.domain.drone.Drone;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.*;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DroneTest {

	private Drone aDrone;
	private String aDroneName = "aDroneName";
	private String aStrDroneID = "aDroneID";

	@Mock
	private Delivery aDelivery;

	@Mock
	private Delivery anotherDelivery;

	@Before
	public void setUp() {
		aDrone = new Drone(aStrDroneID, aDroneName);

		willReturn(true).given(aDelivery).isPending();
		willReturn(true).given(anotherDelivery).isPending();

		aDrone.addDelivery(aDelivery);
		aDrone.addDelivery(anotherDelivery);
	}

	@Test
	public void aDrone_WhenBuilt_ShouldHaveProperID() {
		assertEquals(aStrDroneID, aDrone.getID().getValue());
	}

	@Test
	public void aDrone_WhenBuilt_ShouldHaveProperName() {
		assertEquals(aDroneName, aDrone.getName());
	}

	@Test
	public void aDrone_WhenBuilt_ShouldNotHaveDeliveries() {
		aDrone = new Drone(aStrDroneID, aDroneName);
		List<Delivery> droneDeliveries = aDrone.getAllDeliveries();
		assertEquals(0, droneDeliveries.size());
	}

	@Test
	public void aDrone_WhenAddADelivery_ShouldHaveGivenDelivery() {
		aDrone = new Drone(aStrDroneID, aDroneName);
		aDrone.addDelivery(aDelivery);
		assertTrue(aDrone.getAllDeliveries().contains(aDelivery));
	}

	@Test
	public void aDrone_WhenAskToReturnRemainingDeliveries_ShouldReturnAllDeliveriesWithPendingStatus() {
		List<Delivery> remainingDeliveries = aDrone.getRemainingDeliveries();

		List<Delivery> expectedRemainingDeliveries = new ArrayList<>();
		expectedRemainingDeliveries.add(aDelivery);
		expectedRemainingDeliveries.add(anotherDelivery);

		assertEquals(expectedRemainingDeliveries, remainingDeliveries);
	}

	@Test
	public void aDrone_WhenBuilt_ShouldNotHaveCurrentDelivery() {
		Delivery newCurrentDelivery = aDrone.getCurrentDelivery();
		assertNull(newCurrentDelivery);
	}

	@Test
	public void aDrone_WhenAskToStartNextDelivery_ShouldSetItsCurrentDelivery() {
		aDrone.startNextDelivery();
		Delivery newCurrentDelivery = aDrone.getCurrentDelivery();
		assertEquals(aDelivery, newCurrentDelivery);
	}

	@Test
	public void aDrone_WhenAskToStartNextDelivery_ShouldSetTheCurrentDeliveryStatusAtDoing() {
		aDrone.startNextDelivery();
		aDrone.getCurrentDelivery();
		verify(aDelivery).start();
	}
}