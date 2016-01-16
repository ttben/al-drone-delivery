package app.shipper;

import app.action.CompositeShipperAction;

public class CompositeShipper extends Shipper {

	Shipper livreur;
	CompositeShipperAction actions;
	Shipper drones;

}