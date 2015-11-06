package fr.unice.polytech.si5.al.projet.truck;

import fr.unice.polytech.si5.al.projet.truck.Delivery;
import fr.unice.polytech.si5.al.projet.truck.Drone;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

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
		List<Delivery> droneDeliveries = aDrone.getDeliveries();
		assertEquals(0, droneDeliveries.size());
	}

	@Test
	public void aDrone_WhenAddADelivery_ShouldHaveGivenDelivery() {
		aDrone = new Drone(aStrDroneID, aDroneName);
		aDrone.addDelivery(aDelivery);
		assertTrue(aDrone.getDeliveries().contains(aDelivery));
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
		verify(aDelivery).doing();
	}

	@Test
	public void aDrone_WhenDeclaredAsDead_ShouldFlushAllItsDeliveries() {
		aDrone.declareIsDead();
		assertEquals(0, aDrone.getDeliveries().size());
	}

	@Test
	public void aDrone_WhenDeclaredDeliveryHasFailed_ShouldFailCurrentDelivery() {
		aDrone.startNextDelivery();
		willReturn(true).given(aDelivery).isDoing();

		aDrone.hasFailedDelivery();

		verify(aDelivery).fail();
	}

	@Test
	public void aDrone_WhenDeclaredDeliveryHasFailed_ShouldHasNoCurrentDelivery() {
		aDrone.startNextDelivery();
		willReturn(true).given(aDelivery).isDoing();

		aDrone.hasFailedDelivery();

		assertNull(aDrone.getCurrentDelivery());
	}

	@Test
	public void aDrone_WhenDeclaredDeliveryHasFailed_ShouldAddFailedCurrentDeliveryToFailedDeliveries() {
		aDrone.startNextDelivery();
		willReturn(true).given(aDelivery).isDoing();

		aDrone.hasFailedDelivery();
		List<Delivery> expectedFailedDeliveries = new ArrayList<>();
		expectedFailedDeliveries.add(aDelivery);

		assertEquals(expectedFailedDeliveries, aDrone.getFailedDeliveries());
	}

	@Test(expected = IllegalArgumentException.class)
	public void aDrone_WhenDeclaredDeliveryHasFailedButNoCurrentDeliveryWasDefinedBefore_ShouldThrowAnException() {
		aDrone.hasFailedDelivery();
	}

	@Test(expected = IllegalArgumentException.class)
	public void aDrone_WhenDeclaredDeliveryHasFailedButCurrentDeliveryHasNotDoingStatus_ShouldThrowAnException() {
		willReturn(false).given(aDelivery).isDoing();
		aDrone.hasFailedDelivery();
	}

	@Test
	public void aDrone_WhenDeclaredDeliveryHasFailed_ShouldNotHaveUpdateGlobalDeliveries() {
		aDrone.startNextDelivery();
		willReturn(true).given(aDelivery).isDoing();

		aDrone.hasFailedDelivery();
		List<Delivery> expectedDelivery = new ArrayList<>();
		expectedDelivery.add(aDelivery);
		expectedDelivery.add(anotherDelivery);

		assertEquals(expectedDelivery, aDrone.getDeliveries());
	}


	@Test
	public void aDrone_WhenDeclaredAsDead_ShouldReturnAllItsPendingDeliveries() {
		aDrone = new Drone(aStrDroneID, aDroneName);
		willReturn(false).given(anotherDelivery).isPending();

		List<Delivery> remainingDeliveries = aDrone.declareIsDead();
		List<Delivery> expectedRemainingDeliveries = new ArrayList<>();
		expectedRemainingDeliveries.add(aDelivery);

		assertEquals(0, aDrone.getDeliveries().size());
		assertEquals(0, remainingDeliveries.size());
		assertEquals(remainingDeliveries, aDrone.getDeliveries());
	}

	@Test
	public void aDrone_WhenAskToReturnNextDeliveryToDo_ShouldReturnFirstPendingDelivery() {
		Delivery nextDeliveryToDo = aDrone.getNextDeliveryToDo();
		assertEquals(aDelivery, nextDeliveryToDo);
	}


	@Test(expected = IllegalArgumentException.class)
	public void aDrone_WhenDeclaredIsBackButNoStartDeliveryWasCalledBefore_ShouldThrowException() {
		aDrone.isBack();
	}

	@Test(expected = IllegalArgumentException.class)
	public void aDrone_WhenDeclaredIsBackButCurrentDeliveryHasNotDoingStatus_ShouldThrowException() {
		aDrone.startNextDelivery();
		willReturn(false).given(aDelivery).isDoing();
		aDrone.isBack();
	}

	@Test
	public void aDrone_WhenDeclaredThatIsBack_ShouldTagTheCurrentDeliveryAsDone() {
		aDrone.startNextDelivery();
		willReturn(true).given(aDelivery).isDoing();

		aDrone.isBack();

		verify(aDelivery).done();
	}

	@Test
	public void aDrone_WhenDeclareIsBack_ShouldNotHaveCurrentDelivery() {
		aDrone.startNextDelivery();
		willReturn(true).given(aDelivery).isDoing();

		aDrone.isBack();

		assertNull(aDrone.getCurrentDelivery());
	}

	@Test
	public void ADrone_WhenDeclareIsBack_ShouldHaveUpdateDoneDeliveries() {
		aDrone.startNextDelivery();
		willReturn(true).given(aDelivery).isDoing();

		aDrone.isBack();
		List<Delivery> expectedDoneDelivery = new ArrayList<>();
		expectedDoneDelivery.add(aDelivery);

		assertEquals(expectedDoneDelivery, aDrone.getDoneDeliveries());
	}

	@Test
	public void ADrone_WhenDeclareIsBack_ShouldNotHaveUpdateGlobalDeliveries() {
		aDrone.startNextDelivery();
		willReturn(true).given(aDelivery).isDoing();

		aDrone.isBack();
		List<Delivery> expectedDelivery = new ArrayList<>();
		expectedDelivery.add(aDelivery);
		expectedDelivery.add(anotherDelivery);

		assertEquals(expectedDelivery, aDrone.getDeliveries());
	}
}