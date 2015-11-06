package fr.unice.polytech.si5.al.projet.truck;

import java.util.List;
import java.util.Map;

/**
 * Created by Benjamin on 06/11/2015.
 */
public class Controller {
	private Tour model;
	private View view;

	public Controller() {
		this.model = new Tour(null);
		this.view = new ConsoleView();
	}

	public Map<Drone, List<Delivery>> getCurrentDeliveries() {
		return null;
		//return this.model.getDeliveries();
	}
}
