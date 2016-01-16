package app.action;

import app.shipper.CompositeShipper;

public class SendDrone extends CompositeShipperAction {
	public SendDrone(CompositeShipper target) {
		super(target);
	}

	@Override
	public void execute() {

	}
}