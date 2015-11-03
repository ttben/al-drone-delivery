package fr.unice.polytech.si5.al.projet.s3.truck.step;


import fr.unice.polytech.si5.al.projet.s3.drone.Drone;
import fr.unice.polytech.si5.al.projet.s3.truck.Box;
import fr.unice.polytech.si5.al.projet.s3.truck.DroneDeliveryApp;

import java.util.List;

public class Delivery extends ExecutableStep {

	private String box;
	private String drone;
	private List<String> dronesAlt;

	public Delivery(String name, String box, String drone, List<String> dronesAlt) {
		super(name);
		this.box = box;
		this.drone = drone;
		this.dronesAlt = dronesAlt;
		this.status = TaskStatus.PENDING;
	}

	public void execute(DroneDeliveryApp app) {
		Box boxToDeliver = app.getBoxByID(box);
		Drone droneToUse = app.getDroneByID(drone);

		System.out.println("\t+ Deliver box to " + boxToDeliver.getDestination()
				+ " using " + droneToUse.getID() + ". Weight : (" + boxToDeliver.getWeight() + "kg)");

		this.status = TaskStatus.DONE;
	}

	@Override
	String getDescription() {
		return "(Deliver box " + box + " using " + drone + ")";
	}
}