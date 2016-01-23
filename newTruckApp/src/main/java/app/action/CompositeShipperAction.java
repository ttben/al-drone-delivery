package app.action;

import app.shipper.CompositeShipper;

public abstract class CompositeShipperAction extends Action {
	private CompositeShipper target;

	public CompositeShipperAction(CompositeShipper target, Object ...params) {
		super(params);
		this.target = target;
	}

	@Override
	public CompositeShipper getTarget() {
		return this.target;
	}
}