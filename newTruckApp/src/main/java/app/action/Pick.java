package app.action;

import app.shipper.BasicShipper;

public class Pick extends BasicShipperAction {
	public Pick(BasicShipper target) {
		super(target);
	}

	@Override
	public void execute() {
		System.out.printf("--> %s Picking lol\n", getTarget().toString());
		setChanged();
		notifyObservers(getTarget());	}
}