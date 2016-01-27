package app.action;

import app.shipper.CompositeShipper;

public class CollectDrone extends CompositeShipperAction {

	public CollectDrone(CompositeShipper target, Object... params) {
		super(target, params);
	}

	@Override
	public void execute() {
		setChanged();
		notifyObservers(getTarget());
	}
}