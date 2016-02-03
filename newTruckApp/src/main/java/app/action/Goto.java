package app.action;

import app.shipper.CompositeShipper;
import app.shipper.Shipper;

import java.awt.*;
import java.util.Arrays;

public class Goto extends Action {

	private Dimension location;

	private Shipper target;

	public Goto(Shipper target, Dimension location) {
		super(target, location);
		this.target = target;
		this.location = location;
	}

	public Dimension getLocation() {
		return location;
	}

	@Override
	public Shipper getTarget() {
		return target;
	}

	@Override
	public Object[] getParams() {
		return Arrays.asList(this.location).toArray();
	}
}