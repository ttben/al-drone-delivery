package app.shipper;

import app.action.CompositeShipperAction;

public class CompositeShipper extends Shipper {

	Shipper livreur;
	CompositeShipperAction actions;
	Shipper drones;

	public CompositeShipper(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return "CompositeShipper{" +
				"name='" + name + '\'' +
				'}';
	}
}