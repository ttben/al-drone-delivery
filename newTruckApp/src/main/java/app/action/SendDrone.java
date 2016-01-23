package app.action;

import app.shipper.CompositeShipper;

public class SendDrone extends CompositeShipperAction {

	public SendDrone(CompositeShipper target, Object... params) {
		super(target, params);
	}

	@Override
	public void execute() {

	}
}