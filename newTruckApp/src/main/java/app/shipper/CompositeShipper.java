package app.shipper;

import app.Output;
import app.action.CompositeShipperAction;

public class CompositeShipper extends Shipper {

	Shipper livreur;
	CompositeShipperAction actions;
	Shipper drones;

	public CompositeShipper(String name, Output output) {
		super(name, output);
		output.set(this);
	}

	@Override
	public String toString() {
		return name;
	}
}