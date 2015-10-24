package fr.unice.polytech.si5.al.projet.s3.truck;

import java.util.*;

/**
 * A tour is a set of assignment, and an assignment is a location (drop point location) and a list of tasks
 */
public class Tour {

	private Collection<Delivery> deliveries;

	public Tour() {
		this.deliveries = new ArrayList<Delivery>();
	}

	public Tour(List<Delivery> deliveries) {
		this.deliveries = deliveries;
	}

	public void addDelivery(Delivery delivery) {
		this.deliveries.add(delivery);
	}
}