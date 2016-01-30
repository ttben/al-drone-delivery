package app.shipper;

import app.output.Output;
import app.shipper.BasicShipper;

/**
 * Created by Benjamin on 29/01/2016.
 */
public class HumanShipper extends BasicShipper {
	public HumanShipper(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return getName();
	}
}
