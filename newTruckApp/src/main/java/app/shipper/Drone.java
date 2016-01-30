package app.shipper;

import app.output.Output;
import app.shipper.BasicShipper;

public class Drone extends BasicShipper {

	public Drone(String name, Output output) {
		super(name, output);
	}

	@Override
	public String toString() {
		return getName();
	}

}
