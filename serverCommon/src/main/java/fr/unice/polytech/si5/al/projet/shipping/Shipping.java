package fr.unice.polytech.si5.al.projet.shipping;

import fr.unice.polytech.si5.al.projet.drone.GPSLocation;

public class Shipping {

	private PackageToShip aPackageToShip;
	private GPSLocation target;



	public ShippingStatus getShippingStatus() {
		return shippingStatus;
	}

	public void setShippingStatus(ShippingStatus shippingStatus) {
		this.shippingStatus = shippingStatus;
	}

	public GPSLocation getTarget() {
		return target;
	}

	public void setTarget(GPSLocation target) {
		this.target = target;
	}

	public PackageToShip getaPackageToShip() {
		return aPackageToShip;
	}

	public void setaPackageToShip(PackageToShip aPackageToShip) {
		this.aPackageToShip = aPackageToShip;
	}

	private ShippingStatus shippingStatus;

	public GPSLocation getLocation() {
		return target;
	}

	public Shipping(PackageToShip aPackageToShip, ShippingStatus shippingStatus, GPSLocation target) {
		this.aPackageToShip = aPackageToShip;
		this.shippingStatus = shippingStatus;
		this.target = target;
	}
}