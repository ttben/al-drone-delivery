package app.action;

import app.shipper.BasicShipper;
import app.shipper.CompositeShipper;

import java.util.Map;

/**
 * Created by Benjamin on 12/01/2016.
 */
public class ActionFactory {
	public Action buildAction(String name, Map<String, Object> actionParams) {
		CompositeShipper compositeShipper;
		BasicShipper basicShipper;
		switch(name) {
			case "collect":
				compositeShipper = (CompositeShipper) actionParams.get("shipper");
				basicShipper = (BasicShipper) actionParams.get("second");
				return new CollectDrone(compositeShipper, basicShipper);
			case "send":
				compositeShipper = (CompositeShipper) actionParams.get("shipper");
				basicShipper = (BasicShipper) actionParams.get("second");
				return new SendDrone(compositeShipper, basicShipper);
			case "ship":
				//TODO add location
				compositeShipper = (CompositeShipper) actionParams.get("shipper");
				return new GoToDropPoint(compositeShipper,null);
			case "goto":
				//TODO add location
				basicShipper = (BasicShipper) actionParams.get("shipper");
				return new GoToShippingPosition(basicShipper,null);
			case "drop":
				basicShipper = (BasicShipper) actionParams.get("shipper");
				return new Drop(basicShipper);
			case "pick":
				basicShipper = (BasicShipper) actionParams.get("shipper");
				return new Pick(basicShipper);
			default:
				throw new IllegalArgumentException("Can not build an Action from given description : " + name);
		}
	}
}
