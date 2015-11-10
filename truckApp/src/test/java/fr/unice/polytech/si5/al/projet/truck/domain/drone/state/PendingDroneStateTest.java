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
public class PendingDroneStateTest {

	@Mock
	private Drone aDrone;

	@Mock
	private Delivery aDelivery;

	private DroneState pendingDroneState;


	@Before
	public void setUp() {
		this.pendingDroneState = new PendingDroneState(aDrone);
		willReturn(aDelivery).given(aDrone).getCurrentDelivery();
	}

	@Test
	public void aDrone_WhenInPendingState_ShouldReturnIsPending() {
		assertTrue(pendingDroneState.isPending());
	}

	@Test
	public void aDrone_WhenAskToFly_ShouldShipCurrentDelivery() {
		pendingDroneState.fly();
		verify(aDelivery).ship();
	}

	@Test
	public void aDrone_WhenAskToFly_ShouldReturnFlyDroneState() {
		DroneState newState = pendingDroneState.fly();
		assertTrue(newState instanceof FlyDroneState);
	}

	@Test
	public void aDrone_WhenDeclareFailure_ShouldFlushAllDroneDeliveries() {
		pendingDroneState.declareFailure();
		verify(aDrone).flushAllDeliveries();
	}

	@Test
	public void aDrone_WhenDeclareFailure_ShouldReturnFailureState() {
		DroneState newState = pendingDroneState.declareFailure();
		assertTrue(newState instanceof FailureDeclaredDroneState);
	}
}