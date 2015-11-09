package fr.unice.polytech.si5.al.projet.truck.domain.drone;

/**
 * Created by Benjamin on 04/11/2015.
 */
public class DroneID {
	private String ID;

	public DroneID(String droneID) {
		this.ID = droneID;
	}

	public String getValue() { return this.ID;}

	@Override
	public boolean equals(Object o) {
		if(o instanceof DroneID) {
			DroneID droneID = (DroneID) o;
			return getValue().equalsIgnoreCase(droneID.getValue());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return ID != null ? ID.hashCode() : 0;
	}

	@Override
	public String toString() {
		return this.getValue().toString();
	}
}
