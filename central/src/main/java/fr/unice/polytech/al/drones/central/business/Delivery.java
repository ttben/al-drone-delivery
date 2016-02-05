package fr.unice.polytech.al.drones.central.business;

import fr.unice.polytech.si5.al.projet.central.Address;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 09/11/2015.
 */
public class Delivery {

	String drone;
    String box;
    List<String> droneAlt;
	private Address address;

    public Delivery() {}

    public Delivery(String drone, String box, String droneAlt, Address address) {
        this.drone = drone;
        this.box = box;
        this.droneAlt = new LinkedList<String>();
        this.droneAlt.add(droneAlt);
		this.address = address;
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

	public Address getAddress() {
		return address;
	}
}
