package app.action;

import app.shipper.BasicShipper;

public class GoToShippingPosition extends BasicShipperAction {
	public GoToShippingPosition(BasicShipper target) {
		super(target);
	}

	@Override
	public void execute() {
		System.out.printf("--> %s GoToLocation lol\n", getTarget().toString());
		setChanged();
		notifyObservers(getTarget());
	}
}