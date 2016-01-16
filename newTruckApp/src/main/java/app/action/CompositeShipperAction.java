package app.action;

import app.action.Action;
import app.shipper.CompositeShipper;

public abstract class CompositeShipperAction extends Action {
	private CompositeShipper target;

	public CompositeShipperAction(CompositeShipper target) {
		this.target = target;
	}

	@Override
	public CompositeShipper getTarget() {
		return this.target;
	}
}