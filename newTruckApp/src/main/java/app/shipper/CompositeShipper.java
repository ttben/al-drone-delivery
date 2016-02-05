package app.shipper;

import app.output.Output;
import app.action.CompositeShipperAction;

public class CompositeShipper extends Shipper {

	public CompositeShipper(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return name;
	}
}