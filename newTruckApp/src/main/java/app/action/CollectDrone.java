package app.action;

import app.shipper.CompositeShipper;

public class CollectDrone extends CompositeShipperAction {
	public CollectDrone(CompositeShipper target) {
		super(target);
	}

	@Override
	public void execute() {
		setChanged();
		notifyObservers(getTarget());
	}
}