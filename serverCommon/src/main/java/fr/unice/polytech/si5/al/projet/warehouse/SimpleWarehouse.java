package fr.unice.polytech.si5.al.projet.warehouse;

import fr.unice.polytech.si5.al.projet.drone.GPSLocation;
import fr.unice.polytech.si5.al.projet.central.CentralWarehouse;

public class SimpleWarehouse extends Warehouse {

	public SimpleWarehouse(CentralWarehouse central, GPSLocation location) {
		super(central, location);
	}

}