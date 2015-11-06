package fr.unice.polytech.si5.al.projet.truck;

import java.util.List;

/**
 * Created by Benjamin on 04/11/2015.
 */
public class Tour {
	private DropPoint currentDropPoint;

	public Tour(List<DropPoint> dropPoints) {

		dropPoints.forEach(currentDropPoint -> chain(currentDropPoint,dropPoints.get(0)));
		currentDropPoint = dropPoints.get(0);
	}

	public List<Delivery> getCurrentDeliveries() {
		return this.currentDropPoint.getCurrentDeliveries();
	}

	private void chain(DropPoint dropPoint, DropPoint headOfChain) {
		DropPoint currentDropPoint = headOfChain;

		while(headOfChain.hasNext()) {
			currentDropPoint = currentDropPoint.next();
		}

		currentDropPoint.setNext(dropPoint);
	}
}
