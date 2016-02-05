package app.action;

import app.shipper.BasicShipper;

public class Drop extends BasicShipperAction {
	public Drop(BasicShipper target) {
		super(target);
	}

	@Override
	public void start() {
		System.out.println("Hey . " + target.getName() + " is droping package " + target.getPack() + " duuuuude");

		if(target.getPack() == null || target.getPack().isEmpty()) {
			throw new IllegalStateException("Can not drop a package. I DONT HAVE ONE");
		}
		super.start();
	}

	@Override
	public void end() {
		super.end();
		target.setPack(null);
	}
}