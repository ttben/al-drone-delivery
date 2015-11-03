package fr.unice.polytech.si5.al.projet.s3.drone;

public class ParrotDrone implements Drone {
	private String id;

	public ParrotDrone(String id) {
		this.id = id;
	}

	public boolean checkStatus() {
		return true;
	}

	public String getID() {
		return id;
	}
}