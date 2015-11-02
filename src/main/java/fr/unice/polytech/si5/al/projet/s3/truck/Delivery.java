package fr.unice.polytech.si5.al.projet.s3.truck;


import fr.unice.polytech.si5.al.projet.s3.drone.Drone;

import java.util.List;

public class Delivery {

	private Box box;
	private Drone drone;
	private List<Drone> dronesAlt;
	private TaskStatus status;


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

	public void done() {
		this.status = TaskStatus.DONE;
	}

	public boolean isDone() {
		return this.status == TaskStatus.DONE;
	}

	public boolean failed() {
		return this.status == TaskStatus.ERRORED;
	}
}