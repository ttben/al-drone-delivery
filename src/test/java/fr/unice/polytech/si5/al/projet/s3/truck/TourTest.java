package fr.unice.polytech.si5.al.projet.s3.truck;

import fr.unice.polytech.si5.al.projet.s3.truck.step.ExecutableStep;
import fr.unice.polytech.si5.al.projet.s3.truck.step.Sequence;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TourTest {

	@Mock
	private Sequence aMockedDropPoint;

	@Mock
	private Sequence anotherMockedDropPoint;

	private List<ExecutableStep> dropPointList = new ArrayList<>();

	private Sequence aTour;
	private String aTourName = "A Tour";

	@Before
	public void setUp() {
		buildDropPoints();
	}

	@Ignore
	public void aTour_WhenBuiltWithNoDropPoints_ShouldNotThrow() {
		dropPointList = new LinkedList<>();
		this.aTour = new Sequence(aTourName, dropPointList);
	}

	@Ignore
	public void aTour_WhenBuiltWithDropPoints_ShouldNotThrow() {
		this.aTour = new Sequence(aTourName, dropPointList);
	}

	@Ignore
	public void aTour_WhenBuiltWithDropPoints_ShouldHavePushedThemInPendingQueue() {
		this.aTour = new Sequence(aTourName, dropPointList);
		assertEquals(dropPointList.size(), aTour.getNumberOfDropPointsPending());
		assertEquals(0, aTour.getNumberOfDropPointsDone());
		assertEquals(0, aTour.getNumberOfDropPointsFailed());
	}

	@Ignore
	public void aTour_WhenBuiltWithDropPoints_ShouldHaveSpecifiedDropPointsSortedProperly() {
		this.aTour = new Sequence(aTourName, dropPointList);
		List<ExecutableStep> expected = this.dropPointList;
		assertEquals(expected, this.aTour.getPendingStepQueue());
	}

	@Ignore
	public void aTour_WhenExecuteIsCalledAndPendingQueueIsEmpty_ShouldNotThrow() {
		this.aTour = new Sequence(aTourName, new ArrayList<>());
		this.aTour.execute();
	}

	@Ignore
	public void aTour_WhenExecuteIsCalledAndHeadOfQueueIsPending_ShouldExecuteHeadOfQueue() {
		this.aTour = new Sequence(aTourName, dropPointList);
		this.aTour.execute();
		verify(aMockedDropPoint).execute();
	}

	@Ignore
	public void aTour_WhenExecuteIsCalledAndHeadOfQueueIsDone_ShouldDeleteItOfPendingQueue() {
		this.aTour = new Sequence(aTourName, dropPointList);
		willReturn(true).given(aMockedDropPoint).isDone();

		this.aTour.execute();

		assertEquals(dropPointList.size() - 1, this.aTour.getNumberOfDropPointsPending());
	}

	@Ignore
	public void aTour_WhenExecuteIsCalledAndHeadOfQueueIsDone_ShouldAddItToDoneQueue() {
		this.aTour = new Sequence(aTourName, dropPointList);
		willReturn(true).given(aMockedDropPoint).isDone();

		this.aTour.execute();

		assertEquals(1, this.aTour.getNumberOfDropPointsDone());
		assertEquals(0, this.aTour.getNumberOfDropPointsFailed());
	}

	@Ignore
	public void aTour_WhenExecuteIsCalledAndHeadOfQueueIsDone_ShouldExecuteNextPendingDropPoint() {
		this.aTour = new Sequence(aTourName, dropPointList);
		willReturn(true).given(aMockedDropPoint).isDone();

		this.aTour.execute();

		verify(anotherMockedDropPoint).execute();
	}

	@Ignore
	public void aTour_WhenExecuteIsCalledAndHeadOfQueueIsFailed_ShouldDeleteItOfPendingQueue() {
		this.aTour = new Sequence(aTourName, dropPointList);
		willReturn(true).given(aMockedDropPoint).isFailed();

		this.aTour.execute();

		assertEquals(dropPointList.size() - 1, this.aTour.getNumberOfDropPointsPending());
	}

	@Ignore
	public void aTour_WhenExecuteIsCalledAndHeadOfQueueIsFailed_ShouldAddItToFailedQueue() {
		this.aTour = new Sequence(aTourName, dropPointList);
		willReturn(true).given(aMockedDropPoint).isFailed();

		this.aTour.execute();

		assertEquals(1, this.aTour.getNumberOfDropPointsFailed());
		assertEquals(0, this.aTour.getNumberOfDropPointsDone());
	}

	@Ignore
	public void aTour_WhenExecuteIsCalledAndHeadOfQueueIsFailed_ShouldExecuteNextPendingDropPoint() {
		this.aTour = new Sequence(aTourName, dropPointList);
		willReturn(true).given(aMockedDropPoint).isFailed();

		this.aTour.execute();

		verify(anotherMockedDropPoint).execute();
	}


	@Ignore
	public void aTour_WhenCompleted_AllQueueShouldBeEmpty() {
		this.aTour = new Sequence(aTourName, dropPointList);

		int i = 0;
		while (!aTour.isDone()) {
			when(aTour.getPendingStepQueue().element().isDone()).thenReturn(true);
			aTour.execute();
		}
		assertEquals(0, aTour.getNumberOfDropPointsPending());
	}
	
	@Test
	public void a() throws IOException {
		DroneDeliveryApp d = new DroneDeliveryApp("./src/main/app-in-stub.json");
		d.start();
	}


	//	To Ignore that an exception is raised:
	// @Ignore(expected = Exception)

	private void buildDropPoints() {
		dropPointList.add(aMockedDropPoint);
		dropPointList.add(anotherMockedDropPoint);
	}
}
