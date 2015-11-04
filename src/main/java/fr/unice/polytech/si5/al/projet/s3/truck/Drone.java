package fr.unice.polytech.si5.al.projet.s3.truck;

public class Drone {

	private DroneID ID;
	private String name;

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
}
