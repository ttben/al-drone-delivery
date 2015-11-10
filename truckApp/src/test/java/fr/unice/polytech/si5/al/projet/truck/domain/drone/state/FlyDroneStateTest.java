package fr.unice.polytech.si5.al.projet.truck.domain.drone.state;

import fr.unice.polytech.si5.al.projet.truck.domain.delivery.Delivery;
import fr.unice.polytech.si5.al.projet.truck.domain.drone.Drone;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class FlyDroneStateTest {

	@Mock
	private Drone aDrone;

	@Mock
	private Delivery aDelivery;

	private DroneState flyDroneState;

	@Before
	public void setUp() {
		this.flyDroneState = new FlyDroneState(aDrone);
		willReturn(aDelivery).given(aDrone).getCurrentDelivery();
		willReturn(true).given(aDelivery).isBeingShipped();
	}

	@Test
	public void aDrone_WhenInFlyingState_ShouldReturnIsFlying() {
		assertTrue(flyDroneState.isFlying());
	}

	@Test(expected = IllegalStateException.class)
	public void aDrone_WhenCollectWithoutCurrentDelivery_ShouldThrowException() {
		willReturn(null).given(aDrone).getCurrentDelivery();
		flyDroneState.collect();
	}

	@Test(expected = IllegalStateException.class)
	public void aDrone_WhenCollectWithCurrentDeliveryNotBeingShipped_ShouldThrowException() {
		willReturn(false).given(aDelivery).isBeingShipped();
		flyDroneState.collect();
	}

	@Test
	public void aDrone_WhenCollect_ShouldSetTheCurrentDeliveryStatusToDone() {
		flyDroneState.collect();
		verify(aDelivery).done();
	}

	@Test
	public void aDrone_WhenCollect_ShouldAddDoneDelivery() {
		flyDroneState.collect();
		verify(aDrone).addDoneDelivery(aDelivery);
	}

	@Test
	public void aDrone_WhenCollect_ShouldAskToStartNextDelivery() {
		flyDroneState.collect();
		verify(aDrone).startNextDelivery();
	}

	@Test
	public void aDrone_WhenDeclareFailure_ShouldFlushDeliveries() {
		flyDroneState.declareFailure();
		verify(aDrone).flushAllDeliveries();
	}

	@Test
	public void aDrone_WhenDeclareFailure_ShouldReturnFailureState() {
		DroneState newState = flyDroneState.declareFailure();
		assertTrue(newState instanceof FailureDeclaredDroneState);
	}
}