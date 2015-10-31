package fr.unice.polytech.si5.al.projet.s3.truck;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TourTest {

	private Task firstDropPoint;
	private Task secondDropPoint;
	private List<Task> tourTasks = new ArrayList<>();

	private Task firstDelivery;
	private Task secondDelivery;
	private List<Task> firstDropPointSubTasks = new ArrayList<>();
	private List<Task> secondDropPointSubTasks = new ArrayList<>();

	private Tour aTour;
	private String aTourName = "A Tour";

	@Before
	public void setUp() {
		buildDeliveries();
		buildDropPoints();
	}

	@Test
	public void aTour_WhenBuiltWithNoTasks_ShouldNotThrow() {
		tourTasks = new ArrayList<>();
		this.aTour = new Tour(aTourName, tourTasks);
	}


	@Test
	public void aTour_WhenBuiltWithTasks_ShouldNotThrow() {
		this.aTour = new Tour(aTourName, tourTasks);
	}


	@Test
	public void aTour_WhenBuiltWithTasks_ShouldHaveDeployedIt() {
		this.aTour = new Tour(aTourName, tourTasks);
		assertEquals(tourTasks.size() + firstDropPointSubTasks.size(), this.aTour.getNumberOfTaskOnStack());
	}

	@Test
	public void aTour_WhenBuiltWithTasks_ShouldHaveDeployedItProperly() {
		this.aTour = new Tour(aTourName, tourTasks);
		assertEquals(this.aTour.getTaskStack().peek(), firstDelivery);
	}

	@Test
	public void aTour_WhenExecuteIsCalledAndStackIsEmpty_ShouldNotThrow() {
		tourTasks = new ArrayList<>();
		this.aTour = new Tour(aTourName, tourTasks);
		this.aTour.execute();
	}

	@Test
	public void aTour_WhenExecuteIsCalledAndTopOfStackIsDone_ShouldDeleteIt() {
		this.aTour = new Tour(aTourName, tourTasks);

		firstDelivery.isDone(true);
		this.aTour.execute();

		assertEquals(tourTasks.size() + firstDropPointSubTasks.size() - 1, this.aTour.getNumberOfTaskOnStack());
	}



	//	To test that an exception is raised:
	// @Test(expected = Exception)

	private void buildDropPoints() {
		firstDropPoint = new DropPoint("DropPoint 1 masséna", this.firstDropPointSubTasks);
		secondDropPoint = new DropPoint("DropPoint 2 biot", this.secondDropPointSubTasks);
		tourTasks.add(firstDropPoint);
		tourTasks.add(secondDropPoint);
	}

	private void buildDeliveries() {
		List<Task> tasks = new ArrayList<>();

		firstDelivery = new Delivery("Delivery 1", tasks);
		this.firstDropPointSubTasks.add(firstDelivery);

		secondDelivery = new Delivery("Delivery 2", tasks);
		this.firstDropPointSubTasks.add(secondDelivery);
	}
}
