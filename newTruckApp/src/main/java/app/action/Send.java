package app.action;

import app.shipper.CompositeShipper;

import java.awt.*;

public class Send extends CompositeShipperAction {

	public Send(CompositeShipper target, Object... params) {
		super(target, params);
	}
}