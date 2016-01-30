package app.action;

import app.shipper.CompositeShipper;

import java.awt.*;
import java.util.Arrays;

public class GoToDropPoint extends CompositeShipperAction {

	private Dimension location;

	public GoToDropPoint(CompositeShipper target, Dimension location) {
		super(target);
		this.location = location;
	}

	public Dimension getLocation() {
		return location;
	}

	@Override
	public Object[] getParams() {
		return Arrays.asList(this.location).toArray();
	}
}