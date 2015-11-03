package fr.unice.polytech.si5.al.projet.s3.truck.step;


import fr.unice.polytech.si5.al.projet.s3.drone.Drone;
import fr.unice.polytech.si5.al.projet.s3.truck.Box;

import java.util.List;

public class Delivery extends ExecutableStep {

	private Box box;
	private Drone drone;
	private List<Drone> dronesAlt;

	public Delivery(String name, Box box, Drone drone, List<Drone> dronesAlt) {
		super(name);
		this.box = box;
		this.drone = drone;
		this.dronesAlt = dronesAlt;
		this.status = TaskStatus.PENDING;
	}

	public void execute() {
		System.out.println("Go to " + box.getDestination() + " (" + box.getWeight() + "kg)");
		this.status = TaskStatus.DONE;
	}
}