package app.action;

import app.shipper.CompositeShipper;

public class Collect extends CompositeShipperAction {

	public Collect(CompositeShipper target, Object... params) {
		super(target, params);
	}
}