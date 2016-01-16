package app.action;

import app.shipper.BasicShipper;

public class Drop extends BasicShipperAction {
	public Drop(BasicShipper target) {
		super(target);
	}

	@Override
	public void execute() {
		System.out.printf("--> %s Droping lol\n", getTarget().toString());
		setChanged();
		notifyObservers();
	}
}