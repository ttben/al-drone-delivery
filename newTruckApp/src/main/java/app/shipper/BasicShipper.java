package app.shipper;

import app.output.Output;
import app.action.BasicShipperAction;

import java.awt.*;

public class BasicShipper extends Shipper {

	private Dimension location;
	private String pack;

	public BasicShipper(String name) {
		super(name);
	}

	public Dimension getLocation() {
		return location;
	}

	public void setLocation(Dimension location) {
		this.location = location;
	}

	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}
}