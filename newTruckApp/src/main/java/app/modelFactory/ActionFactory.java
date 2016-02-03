package app.modelFactory;

import app.action.*;
import app.shipper.BasicShipper;
import app.shipper.CompositeShipper;
import app.shipper.Shipper;
import org.json.simple.JSONObject;

import java.awt.*;
import java.util.Map;

/**
 * Created by Benjamin on 12/01/2016.
 */
public class ActionFactory {
	public Action buildAction(Map<String, Shipper> shipperMap, String name, JSONObject actionParams) {
		switch (name) {
			case "collect":
				return buildCollectAction(shipperMap, actionParams);
			case "send":
				return buildSendAction(shipperMap, actionParams);
			case "goto":
				return buildGotoAction(shipperMap, actionParams);
			case "drop":
				return buildDropAction(shipperMap, actionParams);
			case "pick":
				return buildPickAction(shipperMap, actionParams);
			default:
				throw new IllegalArgumentException("Can not build an Action from given description : " + name);
		}
	}

	private Action buildPickAction(Map<String, Shipper> shipperMap, JSONObject actionParams) {
		String targetShipperName = (String) actionParams.get("target");
		BasicShipper basicShipper = (BasicShipper) shipperMap.get(targetShipperName);
		return new Pick(basicShipper);
	}

	private Action buildDropAction(Map<String, Shipper> shipperMap, JSONObject actionParams) {
		String targetShipperName = (String) actionParams.get("target");
		BasicShipper basicShipper = (BasicShipper) shipperMap.get(targetShipperName);
		return new Drop(basicShipper);
	}

	private Action buildGotoAction(Map<String, Shipper> shipperMap, JSONObject actionParams) {
		String targetShipperName = (String) actionParams.get("target");
		Shipper compositeShipper = (Shipper) shipperMap.get(targetShipperName);

		JSONObject locationJsonObject = (JSONObject) actionParams.get("location");
		int xDimension = ((Long) locationJsonObject.get("X")).intValue();
		int yDimension = ((Long) locationJsonObject.get("Y")).intValue();
		Dimension dimension = new Dimension(xDimension, yDimension);
		return new Goto(compositeShipper, dimension);
	}

	private Action buildSendAction(Map<String, Shipper> shipperMap, JSONObject actionParams) {

		String targetShipperName = (String) actionParams.get("target");
		CompositeShipper compositeShipper = (CompositeShipper) shipperMap.get(targetShipperName);

		String basicShipperName = (String) actionParams.get("element");
		BasicShipper basicShipper = (BasicShipper) shipperMap.get(basicShipperName);

		return new Send(compositeShipper, basicShipper);
	}

	private Action buildCollectAction(Map<String, Shipper> shipperMap, JSONObject actionParams) {
		String targetShipperName = (String) actionParams.get("target");
		CompositeShipper compositeShipper = (CompositeShipper) shipperMap.get(targetShipperName);

		String basicShipperName = (String) actionParams.get("element");
		BasicShipper basicShipper = (BasicShipper) shipperMap.get(basicShipperName);

		return new Collect(compositeShipper, basicShipper);
	}
}
