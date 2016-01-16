package app.action;

import app.action.Action;
import app.shipper.BasicShipper;

public abstract class BasicShipperAction extends Action {
	private BasicShipper target;

	public BasicShipperAction(BasicShipper target) {
		this.target = target;
	}

	@Override
	public BasicShipper getTarget() {
		return this.target;
	}
}