package fr.unice.polytech.si5.al.projet.s3.warehouse;

import fr.unice.polytech.si5.al.projet.s3.central.CentralWarehouse;
import fr.unice.polytech.si5.al.projet.s3.drone.GPSLocation;

public class SimpleWarehouse extends Warehouse {

	public SimpleWarehouse(CentralWarehouse central, GPSLocation location) {
		super(central, location);
	}

}