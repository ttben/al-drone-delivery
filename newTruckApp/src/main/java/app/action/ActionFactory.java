package app.action;

import java.util.Map;

/**
 * Created by Benjamin on 12/01/2016.
 */
public class ActionFactory {
	public Action buildAction(String name, Map<String, Object> actionParams) {
		switch(name) {
			case "collect":
				return new CollectDrone(null);
			case "send":
				return new SendDrone(null);
			case "ship":
				return new GoToDropPoint(null, null);
			case "goto":
				return new GoToShippingPosition(null, null);
			case "drop":
				return new Drop(null);
			case "pick":
				return new Pick(null);
			default:
				throw new IllegalArgumentException("Can not build an Action from given description : " + name);
		}
	}
}
