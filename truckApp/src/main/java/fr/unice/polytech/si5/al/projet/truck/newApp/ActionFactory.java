package fr.unice.polytech.si5.al.projet.truck.newApp;

import java.util.Map;

/**
 * Created by Benjamin on 12/01/2016.
 */
public class ActionFactory {
	public Action buildAction(String name, Map<String, Object> actionParams) {
		switch(name) {
			case "collect":
				return new CollectDrone();
			case "send":
				return new SendDrone();
			case "ship":
				return new GoToDropPoint();
			case "goto":
				return new GoToShippingPosition();
			case "drop":
				return new Drop();
			case "pick":
				return new Pick();
			default:
				throw new IllegalArgumentException("Can not build an Action from given description : " + name);
		}
	}
}
