package fr.unice.polytech.si5.al.projet.truck;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TourTest {

	@Test
	public void lol() {
		
	}

	/*

	@Mock
	private Sequence aMockedDropPoint;

	@Mock
	private Sequence anotherMockedDropPoint;

	@Mock
	private DroneDeliveryApp app;
	
	private List<State> dropPointList = new ArrayList<>();

	private Sequence aTour;
	private String aTourName = "A Tour";

	@Before
	public void setUp() {
		buildDropPoints();
	}

	@Test
	public void aTour_WhenBuiltWithNoDropPoints_ShouldNotThrow() {
		dropPointList = new LinkedList<>();
		this.aTour = new Sequence(aTourName, dropPointList);
	}

	@Test
	public void aTour_WhenBuiltWithDropPoints_ShouldNotThrow() {
		this.aTour = new Sequence(aTourName, dropPointList);
	}

	@Test
	public void aTour_WhenBuiltWithDropPoints_ShouldHavePushedThemInPendingQueue() {
		this.aTour = new Sequence(aTourName, dropPointList);
		assertEquals(dropPointList.size(), aTour.getNumberOfDropPointsPending());
		assertEquals(0, aTour.getNumberOfDropPointsDone());
		assertEquals(0, aTour.getNumberOfDropPointsFailed());
	}

	@Test
	public void aTour_WhenBuiltWithDropPoints_ShouldHaveSpecifiedDropPointsSortedProperly() {
		this.aTour = new Sequence(aTourName, dropPointList);
		List<State> expected = this.dropPointList;
		assertEquals(expected, this.aTour.getPendingStepQueue());
	}

	@Test
	public void aTour_WhenExecuteIsCalledAndPendingQueueIsEmpty_ShouldNotThrow() {
		this.aTour = new Sequence(aTourName, new ArrayList<>());
		this.aTour.execute(app);
	}

	@Test
	public void aTour_WhenExecuteIsCalledAndHeadOfQueueIsPending_ShouldExecuteHeadOfQueue() {
		this.aTour = new Sequence(aTourName, dropPointList);
		this.aTour.execute(app);
		verify(aMockedDropPoint).execute(app);
	}

	@Test
	public void aTour_WhenExecuteIsCalledAndHeadOfQueueIsDone_ShouldDeleteItOfPendingQueue() {
		this.aTour = new Sequence(aTourName, dropPointList);
		willReturn(true).given(aMockedDropPoint).isDone();

		this.aTour.execute(app);

		assertEquals(dropPointList.size() - 1, this.aTour.getNumberOfDropPointsPending());
	}

	@Test
	public void aTour_WhenExecuteIsCalledAndHeadOfQueueIsDone_ShouldAddItToDoneQueue() {
		this.aTour = new Sequence(aTourName, dropPointList);
		willReturn(true).given(aMockedDropPoint).isDone();

		this.aTour.execute(app);

		assertEquals(1, this.aTour.getNumberOfDropPointsDone());
		assertEquals(0, this.aTour.getNumberOfDropPointsFailed());
	}

	@Test
	public void aTour_WhenExecuteIsCalledAndHeadOfQueueIsDone_ShouldExecuteNextPendingDropPoint() {
		this.aTour = new Sequence(aTourName, dropPointList);
		willReturn(true).given(aMockedDropPoint).isDone();

		this.aTour.execute(app);

		verify(anotherMockedDropPoint).execute(app);
	}

	@Test
	public void aTour_WhenExecuteIsCalledAndHeadOfQueueIsFailed_ShouldDeleteItOfPendingQueue() {
		this.aTour = new Sequence(aTourName, dropPointList);
		willReturn(true).given(aMockedDropPoint).isFailed();

		this.aTour.execute(app);

		assertEquals(dropPointList.size() - 1, this.aTour.getNumberOfDropPointsPending());
	}

	@Test
	public void aTour_WhenExecuteIsCalledAndHeadOfQueueIsFailed_ShouldAddItToFailedQueue() {
		this.aTour = new Sequence(aTourName, dropPointList);
		willReturn(true).given(aMockedDropPoint).isFailed();

		this.aTour.execute(app);

		assertEquals(1, this.aTour.getNumberOfDropPointsFailed());
		assertEquals(0, this.aTour.getNumberOfDropPointsDone());
	}

	@Test
	public void aTour_WhenExecuteIsCalledAndHeadOfQueueIsFailed_ShouldExecuteNextPendingDropPoint() {
		this.aTour = new Sequence(aTourName, dropPointList);
		willReturn(true).given(aMockedDropPoint).isFailed();

		this.aTour.execute(app);

		verify(anotherMockedDropPoint).execute(app);
	}


	@Test
	public void aTour_WhenCompleted_AllQueueShouldBeEmpty() {
		this.aTour = new Sequence(aTourName, dropPointList);

		int i = 0;
		while (!aTour.isDone()) {
			when(aTour.getPendingStepQueue().element().isDone()).thenReturn(true);
			aTour.execute(app);
		}
		assertEquals(0, aTour.getNumberOfDropPointsPending());
	}

	//	To Ignore that an exception is raised:
	// @Test(expected = Exception)

	private void buildDropPoints() {
		dropPointList.add(aMockedDropPoint);
		dropPointList.add(anotherMockedDropPoint);
	}
	*/
}