package fr.unice.polytech.si5.al.projet.s3.truck.assembly;

import java.util.List;

public class TemplateDelivery {
	public String drone;
	public String box;
	public List<String> droneAlt;

	public TemplateDelivery(String drone, String box, List<String> droneAlt) {
		this.drone = drone;
		this.box = box;
		this.droneAlt = droneAlt;
	}

	public String getDrone() {
		return drone;
	}

	public void setDrone(String drone) {
		this.drone = drone;
	}

	public String getBox() {
		return box;
	}

	public void setBox(String box) {
		this.box = box;
	}

	public List<String> getDroneAlt() {
		return droneAlt;
	}

	public void setDroneAlt(List<String> droneAlt) {
		this.droneAlt = droneAlt;
	}
}