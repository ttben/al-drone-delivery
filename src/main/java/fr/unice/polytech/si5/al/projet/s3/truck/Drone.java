package fr.unice.polytech.si5.al.projet.s3.truck;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Drone {

	private DroneID ID;
	private String name;

	//	Contains all deliveries the drone has (no matter the status)
	private Queue<Delivery> deliveries = new LinkedList<>();

	private Queue<Delivery> pendingDeliveries = new LinkedList<>();

	private Queue<Delivery> doneDeliveries = new LinkedList<>();
	private Queue<Delivery> failedDeliveries = new LinkedList<>();

	private Delivery currentDelivery;

	public Drone(String ID, String name) {
		this.ID = new DroneID(ID);
		this.name = name;
	}


	public String getName() {
		return this.name;
	}

	public DroneID getID() {
		return this.ID;
	}

	public List<Delivery> getDeliveries() {
		return new ArrayList<>(this.deliveries);
	}

	public Delivery getNextDeliveryToDo() {
		return this.pendingDeliveries.peek();
	}

	public Delivery getCurrentDelivery() {
		return this.currentDelivery;
	}

	public List<Delivery> getRemainingDeliveries() {

		//	List<Delivery> pendingDeliveries = new ArrayList<>();
		//	deliveries.stream().filter(delivery -> delivery.isPending()).forEach(delivery -> pendingDeliveries.add(delivery));

		return new ArrayList<>(pendingDeliveries);
	}

	public List<Delivery> getDoneDeliveries() {
		return new ArrayList<>(doneDeliveries);
	}

	public List<Delivery> getFailedDeliveries() {
		return new ArrayList<>(failedDeliveries);
	}

	public void startNextDelivery() {
		this.currentDelivery = this.deliveries.peek();
		this.currentDelivery.doing();
	}


	public void addDelivery(Delivery delivery) {
		this.deliveries.add(delivery);

		if (!delivery.isPending()) {
			throw new IllegalArgumentException("Add a delivery to a drone, that has not a pending status");
		}

		this.pendingDeliveries.add(delivery);
	}

	public void addDeliveries(List<Delivery> deliveryList) {
		deliveryList.forEach(delivery -> addDelivery(delivery));
	}

	public void isBack() {
		if (currentDelivery == null) {
			throw new IllegalArgumentException("A delivery is back, but there is no current delivery for drone " + name);
		}

		if (!currentDelivery.isDoing()) {
			throw new IllegalArgumentException("A delivery with status different from doing is declared as back");
		}

		currentDelivery.done();
		this.doneDeliveries.add(currentDelivery);
		currentDelivery = null;
	}

	public void hasFailedDelivery() {
		if (currentDelivery == null) {
			throw new IllegalArgumentException("A delivery is declared has failed, but there is no current delivery for drone " + name);
		}

		if (!currentDelivery.isDoing()) {
			throw new IllegalArgumentException("A delivery with status different from doing is declared as failed");
		}

		currentDelivery.fail();
		this.failedDeliveries.add(currentDelivery);
		currentDelivery = null;
	}


	public List<Delivery> declareIsDead() {
		List<Delivery> remainingDeliveries = getRemainingDeliveries();

		this.flushAllDeliveries();

		return remainingDeliveries;
	}

	public void flushAllDeliveries() {
		this.deliveries = new LinkedList<>();
		this.pendingDeliveries = new LinkedList<>();
		this.doneDeliveries = new LinkedList<>();
		this.failedDeliveries = new LinkedList<>();
	}
}