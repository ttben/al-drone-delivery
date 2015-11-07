package fr.unice.polytech.si5.al.projet.truck;

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
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DroneID droneID = (DroneID) o;

		return !(ID != null ? !ID.equals(droneID.ID) : droneID.ID != null);

	}

	@Override
	public int hashCode() {
		return ID != null ? ID.hashCode() : 0;
	}
}
