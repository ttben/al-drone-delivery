package app;

import app.shipper.BasicShipper;

/**
 * Created by Benjamin on 29/01/2016.
 */
public class HumanShipper extends BasicShipper {
	public HumanShipper(String name, Output output) {
		super(name, output);
	}

	@Override
	public String toString() {
		return getName();
	}
}
