package app.shipper;

import app.output.Output;
import app.action.BasicShipperAction;

import java.awt.*;

public class BasicShipper extends Shipper {

	private Dimension location;
	private Package pack;

	public BasicShipper(String name) {
		super(name);
	}

	public Dimension getLocation() {
		return location;
	}

	public void setLocation(Dimension location) {
		this.location = location;
	}

	public Package getPack() {
		return pack;
	}

	public void setPack(Package pack) {
		this.pack = pack;
	}
}