package fr.unice.polytech.si5.al.projet.shipping;

import fr.unice.polytech.si5.al.projet.central.Address;

public class PackageToShip {

	private Dimensions dimensions;
	private Weight weight;

	private Address address;

	public PackageToShip(Address address, Weight weight, Dimensions dimensions) {
		this.address = address;
		this.weight = weight;
		this.dimensions = dimensions;
	}

	public Dimensions getDimensions() {
		return dimensions;
	}

	public Weight getWeight() {
		return weight;
	}

	public Address getAddress() {
		return address;
	}
}