package fr.unice.polytech.si5.al.projet.s3.truck;

import java.util.*;

public class Drone {

	private DroneID ID;
	private String name;

	private Queue<Delivery> deliveries = new LinkedList<>();
	private List<Delivery> doneDeliveries = new ArrayList<>();
	private List<Delivery> failDeliveries = new ArrayList<>();

	public Drone(String ID, String name) {
		this.ID = new DroneID(ID);
		this.name = name;
	}

	public void isBack() {
		Delivery deliveryDone = deliveries.poll();
		doneDeliveries.add(deliveryDone);
	}

	public void hasFailedDelivery() {
		Delivery failedDelivery = deliveries.poll();
		failDeliveries.add(failedDelivery);
	}

	public void addDelivery(Delivery delivery) {
		this.deliveries.add(delivery);
	}

	public void addDeliveries(List<Delivery> deliveryList) {
		this.deliveries.addAll(deliveryList);
	}

	public Delivery getCurrentDelivery() {
		return this.deliveries.element();
	}

	public List<Delivery> declareIsDead() {
		List<Delivery> remainingDeliveries = new ArrayList<>();
		Collections.copy(remainingDeliveries,new ArrayList<>(deliveries));

		this.deliveries = new LinkedList<>();

		return remainingDeliveries;
	}

	public String getName() {
		return this.name;
	}

	public DroneID getID() {
		return this.ID;
	}
}
