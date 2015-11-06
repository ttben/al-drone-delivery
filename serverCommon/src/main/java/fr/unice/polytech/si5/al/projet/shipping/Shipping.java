package fr.unice.polytech.si5.al.projet.shipping;

import fr.unice.polytech.si5.al.projet.drone.GPSLocation;

public class Shipping {

	private PackageToShip aPackageToShip;
	private GPSLocation target;
	private ShippingStatus shippingStatus;

	public GPSLocation getLocation() {
		return target;
	}
}