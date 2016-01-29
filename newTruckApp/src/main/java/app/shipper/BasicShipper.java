package app.shipper;

import app.Output;
import app.action.BasicShipperAction;

public class BasicShipper extends Shipper {

	BasicShipperAction actions;

	public BasicShipper(String name, Output output) {
		super(name, output);
	}
}