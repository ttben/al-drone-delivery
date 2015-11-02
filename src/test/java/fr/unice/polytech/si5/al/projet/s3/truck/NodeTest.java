package fr.unice.polytech.si5.al.projet.s3.truck;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class NodeTest {

	private Node firstDropPoint;
	private Node secondDropPoint;
	private List<Node> tourTasks = new ArrayList<>();

	private Delivery firstDelivery;
	private Delivery secondDelivery;
	private Delivery thirdDelivery;
	private Delivery fourthDelivery;
	private List<Node> firstDropPointSubTasks = new ArrayList<>();
	private List<Node> secondDropPointSubTasks = new ArrayList<>();

	private Node aTour;
	private String aTourName = "A Tour";

	@Before
	public void setUp() {
		buildDeliveries();
		buildDropPoints();
	}

	@Test
	public void aTour_WhenBuiltWithNoTasks_ShouldNotThrow() {
		tourTasks = new ArrayList<>();
		this.aTour = new Node(aTourName, tourTasks);
	}


	@Test
	public void aTour_WhenBuiltWithTasks_ShouldNotThrow() {
		this.aTour = new Node(aTourName, tourTasks);
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
		this.aTour = new Node(aTourName, tourTasks);
		this.aTour.doNext();
	}

	@Test
	public void aTour_WhenDoNextIsCalledAndNotEmptyTree_ShouldDoFirstDelivery(){
		this.aTour = new Node(aTourName,tourTasks);
		aTour.doNext();
	}



	//	To test that an exception is raised:
	// @Test(expected = Exception)

	private void buildDropPoints() {
		firstDropPoint = new Node("DropPoint 1 masséna", this.firstDropPointSubTasks);
		secondDropPoint = new Node("DropPoint 2 biot", this.secondDropPointSubTasks);
		tourTasks.add(firstDropPoint);
		tourTasks.add(secondDropPoint);
	}

	private void buildDeliveries() {
		Package aPackage = new Package();

		firstDelivery = new Delivery("Delivery 1", aPackage);
		this.firstDropPointSubTasks.add(firstDelivery);

		secondDelivery = new Delivery("Delivery 2", aPackage);
		this.firstDropPointSubTasks.add(secondDelivery);

		thirdDelivery = new Delivery("Delivery 3", aPackage);
		this.secondDropPointSubTasks.add(thirdDelivery);

		fourthDelivery = new Delivery("Delivery 4", aPackage);
		this.secondDropPointSubTasks.add(fourthDelivery);

		Node other = new Delivery("Delivery 5", aPackage);
		this.secondDropPointSubTasks.add(other);
	}
}
