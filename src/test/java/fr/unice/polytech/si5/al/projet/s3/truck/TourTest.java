package fr.unice.polytech.si5.al.projet.s3.truck;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TourTest {

	@Mock
	private Task aDropPoint;
	private List<Task> dropPointList = new ArrayList<>();

	@Mock
	private Task aDelivery;
	private List<Task> deliveries = new ArrayList<>();

	private Tour tour;

	@Before
	public void setUp() {
		buildDropPoints();
		buildDeliveries();
	}

	@Test
	public void aTour_WhenBuiltWithNoTasks_ShouldNotThrow() {
		dropPointList = new ArrayList<>();
		this.tour = new Tour(dropPointList);
	}

	@Test
	public void aTour_WhenBuiltWithTasks_ShouldHaveDropPoint() {
		this.tour = new Tour(dropPointList);
		assertEquals(1,this.tour.getTaskStack().size());
	}

	@Test
	public void aTour_WhenExecuteIsCalled_ShouldCallDevelopOnTopOfTheStack() {
		this.tour = new Tour(dropPointList);
		this.tour.execute();
		verify(aDropPoint).develop();
	}

	@Test
	public void aTour_WhenExecuteIsCalled_ShouldPushTasksOnTopOfTheStack() {
		this.tour = new Tour(dropPointList);
		this.tour.execute();
		assertEquals(2, this.tour.getTaskStack().size());
	}

	@Test
	public void aTour_WhenExecuteIsCalled_ShouldCallExecuteOnTopOfTheStack() {
		this.tour = new Tour(dropPointList);
		this.tour.execute();
		verify(aDelivery).execute();
	}

	@Test
	public void aTour_WhenExecuteIsCalledAndStackIsEmpty_ShouldNotThrow() {
		dropPointList = new ArrayList<>();
		this.tour = new Tour(dropPointList);
		this.tour.execute();
	}

	//	To test that an exception is raised:
	// @Test(expected = Exception)

	private void buildDropPoints() {
		dropPointList.add(aDropPoint);
	}

	private void buildDeliveries() {
		//	Add mock to list of mocks
		this.deliveries.add(aDelivery);

		//	DropPoint#develop will return the list of mocks
		willReturn(deliveries).given(aDropPoint).develop();
	}
}
