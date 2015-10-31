package fr.unice.polytech.si5.al.projet.s3.truck;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TourTest {

	private DropPoint firstDropPoint;
	private DropPoint secondDropPoint;
	private List<Node> tourTasks = new ArrayList<>();

	private Delivery firstDelivery;
	private Delivery secondDelivery;
	private Delivery thirdDelivery;
	private Delivery fourthDelivery;
	private List<Node> firstDropPointSubTasks = new ArrayList<>();
	private List<Node> secondDropPointSubTasks = new ArrayList<>();

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


	/*@Test
	NOT USEFUL ANYMORE
	public void aTour_WhenBuiltWithTasks_ShouldHaveDeployedIt() {
		this.aTour = new Tour(aTourName, tourTasks);
		assertEquals(tourTasks.size() + firstDropPointSubTasks.size(), this.aTour.getNumberOfTaskOnStack());
	}*/

	/*
	@Test
	NOT USEFUL ANYMORE
	public void aTour_WhenBuiltWithTasks_ShouldHaveDeployedItProperly() {
		this.aTour = new Tour(aTourName, tourTasks);
		assertEquals(this.aTour.getTaskStack().peek(), firstDelivery);
	}*/

	@Test
	public void aTour_WhenNextIsCalledAndStackIsEmpty_ShouldNotThrow() {
		tourTasks = new ArrayList<>();
		this.aTour = new Tour(aTourName, tourTasks);
		this.aTour.doNext();
	}

	@Test
	public void aTour_WhenDoNextIsCalledAndNotEmptyTree_ShouldDoFirstDelivery(){
		this.aTour = new Tour(aTourName,tourTasks);
		while (!aTour.isDone()){
			aTour.doNext();
		}
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
		List<Node> packages = new ArrayList<>();

		firstDelivery = new Delivery("Delivery 1", packages);
		this.firstDropPointSubTasks.add(firstDelivery);

		secondDelivery = new Delivery("Delivery 2", packages);
		this.firstDropPointSubTasks.add(secondDelivery);

		thirdDelivery = new Delivery("Delivery 3", packages);
		this.secondDropPointSubTasks.add(thirdDelivery);

		fourthDelivery = new Delivery("Delivery 4", packages);
		this.secondDropPointSubTasks.add(fourthDelivery);
	}
}
