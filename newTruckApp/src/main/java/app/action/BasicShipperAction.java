package app.action;

import app.action.Action;
import app.shipper.BasicShipper;

public abstract class BasicShipperAction extends Action {

	protected BasicShipper target;

	public BasicShipperAction(BasicShipper target, Object[] ...params) {
		super(params);
		this.target = target;
	}

	@Override
	public BasicShipper getTarget() {
		return this.target;
	}
}