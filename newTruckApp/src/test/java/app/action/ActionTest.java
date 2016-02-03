package app.action;

import app.output.Output;
import app.shipper.BasicShipper;
import app.shipper.CompositeShipper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ActionTest {

	@Mock
	private CompositeShipper compositeShipperMocked;

	@Mock
	private BasicShipper basicShipper;

	@Mock
	private Output outputMock;

	private CompositeShipperAction collectDrone;

	@Before
	public void setUp() {
		collectDrone = new Collect(compositeShipperMocked, basicShipper);
		collectDrone.addObserver(outputMock);
		when(compositeShipperMocked.getName()).thenReturn("compositeShipperMocked");
	}

	@Test
	public void anAction_WhenStartIsCalled_ShouldNotifyObservers() {
		collectDrone.start();
		verify(outputMock).update(any(), any());
	}

	@Test
	public void anAction_WhenStartIsCalled_ShouldNotifyObserversWithProperParameter() {
		collectDrone.start();
		verify(outputMock).update(collectDrone, ActionEvent.STARTED);
	}

	@Test
	public void anAction_WhenEndIsCalled_ShouldNotifyObservers() {
		collectDrone.end();
		verify(outputMock).update(any(), any());
	}

	@Test
	public void anAction_WhenEndIsCalled_ShouldNotifyObserversWithProperParameter() {
		collectDrone.end();
		verify(outputMock).update(collectDrone, ActionEvent.ENDED);
	}

	@Test
	public void anAction_WhenQueuing_ShouldQueueItSelfIntoItsInternalTarget() {
		collectDrone.queue();
		verify(compositeShipperMocked).queueAction(collectDrone);
	}

}