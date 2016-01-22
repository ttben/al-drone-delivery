package app;

import app.shipper.BasicShipper;

/**
 * Created by Benjamin on 16/01/2016.
 */
public class Drone extends BasicShipper {

	private String name;

	public Drone(String name) {
		super(name);
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return getName();
	}

}
