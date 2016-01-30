package app.shipper;

import app.output.Output;
import app.shipper.BasicShipper;

public class Drone extends BasicShipper {

	public Drone(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return getName();
	}

}
