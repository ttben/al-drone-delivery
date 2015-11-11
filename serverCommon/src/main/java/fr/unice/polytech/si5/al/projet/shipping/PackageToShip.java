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

	public void setDimensions(Dimensions dimensions) {
		this.dimensions = dimensions;
	}

	public void setWeight(Weight weight) {
		this.weight = weight;
	}

	public void setAddress(Address address) {
		this.address = address;
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

	public PackageToShip() {
	}
}