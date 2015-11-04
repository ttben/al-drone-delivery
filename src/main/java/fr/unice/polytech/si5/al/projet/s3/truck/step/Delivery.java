package fr.unice.polytech.si5.al.projet.s3.truck.step;


import fr.unice.polytech.si5.al.projet.s3.truck.*;

import java.util.List;

public class Delivery extends ExecutableStep {
	private DeliveryID ID;

	private Box box;
	private Drone drone;
	private List<Drone> dronesAlt;

	private Delivery next;
	private Delivery prev;

	public Delivery(DeliveryID ID, String name, Box box, Drone drone, List<Drone> dronesAlt) {
		super(name);
		this.ID = ID;
		this.box = box;
		this.drone = drone;
		this.dronesAlt = dronesAlt;
		this.status = TaskStatus.PENDING;
	}

	public DeliveryID getID() {
		return this.ID;
	}

	public void start() {
		this.start();
	}

	public boolean validate(DroneID droneID, String boxID) {
		//TODO CHECK IF DRONE AND BOX ARE OK
		//	if(checkOK) validate();
		return true;
	}

	public void setPrev(Delivery prev) {
		this.prev = prev;
	}

	public void setNext(Delivery next) {
		this.next = next;
	}

	public boolean hasPrev() {
		return this.prev != null;
	}

	public boolean hasNext() {
		return this.next != null;
	}

	public Delivery next() {
		return this.next;
	}

	public void execute(DroneDeliveryApp app) {

		System.out.println("\t+ Deliver box to " + box.getDestination()
				+ " using " + drone.getID() + ". Weight : (" + box.getWeight() + "kg)");

		this.status = TaskStatus.DONE;
	}

	@Override
	String getDescription() {
		return "(Deliver box " + box + " using " + drone + ")";
	}

	public DroneID getDroneID() {
		return this.drone.getID();
	}

	public Drone getDrone() {
		return drone;
	}
}