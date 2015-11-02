package fr.unice.polytech.si5.al.projet.s3.truck;

import fr.unice.polytech.si5.al.projet.s3.drone.ParrotDrone;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TourTest {

	private DropPoint firstDropPoint;
	private DropPoint secondDropPoint;
	private List<DropPoint> dropPointList = new ArrayList<>();


	private Delivery firstDelivery;
	private Delivery secondDelivery;
	private List<Delivery> firstDropPointDeliveries = new ArrayList<>();
	private List<Delivery> secondDropPointDeliveries = new ArrayList<>();

	private Tour aTour;
	private String aTourName = "A Tour";

	@Before
	public void setUp() {
		buildDeliveries();
		buildDropPoints();
	}

	@Test
	public void aTour_WhenBuiltWithNoTasks_ShouldNotThrow() {
		dropPointList = new ArrayList<>();
		this.aTour = new Tour(aTourName, dropPointList);
	}


	@Test
	public void aTour_WhenBuiltWithTasks_ShouldNotThrow() {
		this.aTour = new Tour(aTourName, dropPointList);
	}


	@Test
	public void aTour_WhenExecuteIsCalledAndStackIsEmpty_ShouldNotThrow() {
		dropPointList = new ArrayList<>();
		this.aTour = new Tour(aTourName, dropPointList);
		this.aTour.execute();
	}

	@Test
	public void aTour_WhenExecuteIsCalledAndTopOfStackIsDone_ShouldDeleteIt() {
		this.aTour = new Tour(aTourName, dropPointList);
		firstDelivery.done();
		this.aTour.execute();
		// We expect size-2 because the first delivery is done and the second is executed (and done)
		assertEquals(firstDropPointDeliveries.size() - 2, firstDropPoint.getDeliveryQueue().size());
	}

	@Test
	public void aTour_WhenCompleted_AllQueueShouldBeEmpty() {
		this.aTour = new Tour(aTourName, dropPointList);
		while (!aTour.isDone()) {
			aTour.execute();
		}
		assertEquals(0, aTour.getNumberOfDropPointOnQueue());
		assertEquals(0, firstDropPoint.getDeliveryQueue().size());
	}


	//	To test that an exception is raised:
	// @Test(expected = Exception)

	private void buildDropPoints() {
		firstDropPoint = new DropPoint("DropPoint 1 masséna", this.firstDropPointDeliveries);
		secondDropPoint = new DropPoint("DropPoint 2 biot", this.secondDropPointDeliveries);
		dropPointList.add(firstDropPoint);
		dropPointList.add(secondDropPoint);
	}

	private void buildDeliveries() {
		///List<Task> tasks = new ArrayList<>();

		firstDelivery = new Delivery(new Box("rue du choux pointu", 2.5), new ParrotDrone(), new ArrayList<>());
		this.firstDropPointDeliveries.add(firstDelivery);

		secondDelivery = new Delivery(new Box("Avenue des flan moisis", 0.35), new ParrotDrone(), new ArrayList<>());
		this.firstDropPointDeliveries.add(secondDelivery);
	}
}
