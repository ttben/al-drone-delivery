package fr.unice.polytech.si5.al.projet.s3.truck;


import fr.unice.polytech.si5.al.projet.s3.drone.Drone;

import java.util.List;

public class Delivery extends Step {

	private Box box;
	private Drone drone;
	private List<Drone> dronesAlt;

	public Delivery(Box box, Drone drone, List<Drone> dronesAlt) {
		this.box = box;
		this.drone = drone;
		this.dronesAlt = dronesAlt;
		this.status = TaskStatus.PENDING;
	}

	public void execute() {
		this.status = TaskStatus.PROCESSING;
		System.out.println("Go to " + box.getDestination() + " (" + box.getWeight() + "kg)");
		this.done();
	}
}