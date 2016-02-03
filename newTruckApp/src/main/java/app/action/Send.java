package app.action;

import app.shipper.BasicShipper;
import app.shipper.CompositeShipper;

public class Send extends CompositeShipperAction {

	public Send(CompositeShipper target, Object... params) {
		super(target, (BasicShipper) params[0], params);
		String packageID = (String) params[1];
		element.setPack(packageID);
		System.out.println("element  " + element);
		System.out.println("packageID  " + packageID);
	}
}
