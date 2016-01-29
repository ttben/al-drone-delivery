package app.shipper;

import app.action.Action;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ShipperTest {

	public static final String SHIPPER_NAME = "A shipper name";

	@Mock
	private Action aMockedAction;

	@Mock
	private Action anotherMockedAction;

	private Shipper aShipper;

	private Queue<Action> expectedListOfActions;

	@Before
	public void setUp() {
		aShipper = new Shipper(SHIPPER_NAME);
		expectedListOfActions = new LinkedList<>();
	}


	@Test
	public void aShipper_WhenBuilt_ShouldHaveProperName() {
		String expectedName = SHIPPER_NAME;
		String actualName = aShipper.getName();
		assertEquals(expectedName, actualName);
	}

	@Test
	public void aShipper_WhenBuilt_ShouldHaveANonNullEmptyQueueListOfActions() {
		Queue<Action> expectedListOfActions = new LinkedList<>();
		Queue<Action> actualListOfActions = aShipper.getActionsQueue();
		assertEquals(expectedListOfActions, actualListOfActions);
	}

	@Test
	public void aShipper_WhenBuilt_ShouldNotHaveCurrentAction() {
		Action currentAction = aShipper.getCurrentAction();
		assertNull(currentAction);
	}

	@Test
	public void aShipper_WhenQueuingAnAction_ShouldActuallyQueueIt() {
		aShipper.setCurrentAction(aMockedAction);	//	Only to force 'doStart' boolean to false an don't "poll" action
		aShipper.queueAction(aMockedAction);

		expectedListOfActions.add(aMockedAction);
		Queue<Action> currentListOfActions = aShipper.getActionsQueue();

		assertEquals(expectedListOfActions, currentListOfActions);
	}

	@Test
	public void aShipper_WhenCurrentActionIsNotNullAndDoNotHaveActionLeft_ShouldNotStartNextAction() {
		aShipper.setCurrentAction(aMockedAction);	//	Only to force 'doStart' boolean to false an don't "poll" action
		aShipper.queueAction(aMockedAction);

		int expectedNumberOfActions = 1;
		int currentNumberOfActions = aShipper.getActionsQueue().size();

		assertEquals(expectedNumberOfActions, currentNumberOfActions);
	}

	@Test
	public void aShipper_WhenQueuingIfCurrentActionIsNullAndDoNotHaveActionLeft_ShouldNotStartNextAction() {
		aShipper.getActionsQueue().add(aMockedAction);	//	Only to force 'doStart' boolean to false an don't "poll" action
		aShipper.queueAction(anotherMockedAction);

		int expectedNumberOfActions = 2;
		int currentNumberOfActions = aShipper.getActionsQueue().size();

		assertEquals(expectedNumberOfActions, currentNumberOfActions);
	}

	@Test
	public void aShipper_WhenQueuingActionAndNoActionAreLeft_ShouldStartNextAction() {
		aShipper.queueAction(anotherMockedAction);

		int expectedNumberOfActions = 0;
		int currentNumberOfActions = aShipper.getActionsQueue().size();

		assertEquals(expectedNumberOfActions, currentNumberOfActions);
	}

	@Test
	public void aShipper_WhenStartNextAction_ShouldActuallyStartNextAction() {
		aShipper.getActionsQueue().add(aMockedAction);

		aShipper.startNextAction();

		verify(aMockedAction).start();
	}

	@Test
	public void aShipper_WhenStartNextActionAndThereIsNoPendingOne_ShouldActuallyDoNothing() {
		aShipper.startNextAction();

		int expectedNumberOfActions = 0;
		int currentNumberOfActions = aShipper.getActionsQueue().size();

		assertEquals(expectedNumberOfActions, currentNumberOfActions);
	}

	@Test
	public void aShipper_WhenStartNextAction_ShouldDeletingItFromItsInternalQueue() {
		aShipper.getActionsQueue().add(aMockedAction);
		aShipper.startNextAction();

		boolean expectedValueOfEmpty = true;
		boolean currentValueOfEmpty = aShipper.getActionsQueue().isEmpty();

		assertEquals(expectedValueOfEmpty, currentValueOfEmpty);
	}

	@Test(expected = RuntimeException.class)
	public void aShipper_WhenStartNextActionIsCalledAndCurrentActionIsNotNull_ShouldThrowRuntimeException() {
		aShipper.setCurrentAction(aMockedAction);
		aShipper.startNextAction();
	}

	public void aShipper_WhenEndAction_ShouldActuallyCallEndOnCurrentAction() {
		aShipper.setCurrentAction(aMockedAction);
		aShipper.endAction();
		verify(aMockedAction).end();
	}

	public void aShipper_WhenEndActionAndNothingLeft_ShouldHaveANullCurrentAction() {
		aShipper.setCurrentAction(aMockedAction);
		aShipper.endAction();
		assertNull(aShipper.getCurrentAction());
	}

	@Test(expected = RuntimeException.class)
	public void aShipper_WhenEndActionIsCalledAndCurrentActionIsNull_ShouldThrowRuntimeException() {
		aShipper.endAction();
	}
}