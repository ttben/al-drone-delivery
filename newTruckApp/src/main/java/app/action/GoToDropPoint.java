package app.action;

import app.shipper.CompositeShipper;

public class GoToDropPoint extends CompositeShipperAction {
	public GoToDropPoint(CompositeShipper target) {
		super(target);
	}

	@Override
	public void execute() {
		setChanged();
		notifyObservers(getTarget());
	}
}