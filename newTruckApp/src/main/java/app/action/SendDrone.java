package app.action;

import app.shipper.CompositeShipper;

import java.awt.*;

public class SendDrone extends CompositeShipperAction {
	public SendDrone(CompositeShipper target) {
		super(target);
	}

	@Override
	public void execute() {
		setChanged();
		notifyObservers(getTarget());
	}


}