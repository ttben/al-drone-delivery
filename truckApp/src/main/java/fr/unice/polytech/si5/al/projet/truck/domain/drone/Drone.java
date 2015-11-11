package fr.unice.polytech.si5.al.projet.truck.domain.drone;

import fr.unice.polytech.si5.al.projet.truck.domain.delivery.Delivery;
import fr.unice.polytech.si5.al.projet.truck.domain.drone.state.DroneState;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Drone {

	private DroneID ID;
	private String name;

	private DroneState droneState;

	//	Contains all deliveries the drone has (no matter the status)
	private Queue<Delivery> deliveries = new LinkedList<>();
	private Queue<Delivery> pendingDeliveries = new LinkedList<>();
	private Delivery currentDelivery;
	private Queue<Delivery> doneDeliveries = new LinkedList<>();
	private Queue<Delivery> failedDeliveries = new LinkedList<>();

	public Drone(String ID, String name) {
		this.ID = new DroneID(ID);
		this.name = name;
		this.droneState = DroneState.getDefaultState(this);
	}

	public String getName() {
		return this.name;
	}

	public DroneID getID() {
		return this.ID;
	}

	public List<Delivery> getAllDeliveries() {
		return new ArrayList<>(this.deliveries);
	}

	public Delivery getNextDeliveryToDo() {
		return this.pendingDeliveries.peek();
	}

	public Delivery getCurrentDelivery() {
		return this.currentDelivery;
	}

	public List<Delivery> getRemainingDeliveries() {
		List<Delivery> remainingDeliveries = new ArrayList<>(pendingDeliveries);
		if(currentDelivery != null) {
			remainingDeliveries.add(currentDelivery);
		}
		return remainingDeliveries;
	}

	public List<Delivery> getDoneDeliveries() {
		return new ArrayList<>(doneDeliveries);
	}

	public List<Delivery> getFailedDeliveries() {
		return new ArrayList<>(failedDeliveries);
	}

	public void addDelivery(Delivery delivery) {
		if (delivery.isFailed()) {
			throw new IllegalArgumentException("Add a delivery to a drone, that has failed");
		}

		this.deliveries.add(delivery);
		this.pendingDeliveries.add(delivery);
	}

	public void addDeliveries(List<Delivery> deliveryList) {
		deliveryList.forEach(delivery -> addDelivery(delivery));
	}

	public void addDoneDelivery(Delivery doneDelivery) {
		this.doneDeliveries.add(doneDelivery);
	}

	public void addFailedDelivery(Delivery failedDelivery) {
		this.failedDeliveries.add(failedDelivery);
	}

	public void startNextDelivery() {
		if(currentDelivery != null) {
			throw new IllegalStateException("Can not start a delivery when a current delivery has been set");
		}

		this.currentDelivery = this.pendingDeliveries.poll();
		if(this.currentDelivery != null) {
			this.currentDelivery.start();
		}
		else {
			System.out.println("(drone " + this.name + " has nothing left to do)");
		}

	}

	public void isBack() {
		this.droneState = this.droneState.collect();
	}

	public List<Delivery> declareIsDead() {
		List<Delivery> remainingDeliveries = getRemainingDeliveries();
		this.droneState = this.droneState.declareFailure();
		return remainingDeliveries;
	}

	public boolean isAlive(){
		return this.droneState.isAlive();
	}

	public boolean isGone() {
		return this.droneState.isFlying();
	}

	public void fly() {
		this.droneState = droneState.fly();
	}

	public boolean isAvailable() {
		return this.droneState.isAlive() && this.droneState.isPending();
	}

	public void hasFailedDelivery() {
		Delivery currentDelivery = this.currentDelivery;

		if (currentDelivery == null) {
			throw new IllegalArgumentException("A delivery is declared has failed, but there is no current delivery for drone " + this.name);
		}

		currentDelivery.fail();

		this.addFailedDelivery(currentDelivery);
		this.startNextDelivery();
	}

	public void flushAllDeliveries() {
		this.deliveries = new LinkedList<>();
		this.currentDelivery = null;
		this.pendingDeliveries = new LinkedList<>();
		this.doneDeliveries = new LinkedList<>();
		this.failedDeliveries = new LinkedList<>();
	}

	public void deleteCurrentDelivery() {
		this.currentDelivery = null;
	}

	public boolean hasDeliveriesLeft() {
		return this.pendingDeliveries.size() > 0 || this.currentDelivery != null;
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof Drone) {
			Drone d = (Drone)o;
			return d.ID.equals(ID);
		}

		return false;
	}

	@Override
	public String toString() {
		return "#" + this.ID.toString() + " " + this.name + " --- current : " + this.currentDelivery + " -- pending : " + this.pendingDeliveries;
	}
}