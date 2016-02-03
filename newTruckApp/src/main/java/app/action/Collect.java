package app.action;

import app.shipper.BasicShipper;
import app.shipper.CompositeShipper;

public class Collect extends CompositeShipperAction {

	public Collect(CompositeShipper target, Object... params) {
		super(target, (BasicShipper)params[0], params);
	}
}