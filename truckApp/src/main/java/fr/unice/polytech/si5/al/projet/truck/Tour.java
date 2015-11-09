package fr.unice.polytech.si5.al.projet.truck;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Benjamin on 04/11/2015.
 */
public class Tour {
	private DropPoint currentDropPoint;

	private boolean started;

	public Tour(List<DropPoint> dropPoints) {
		dropPoints.forEach(currentDropPoint -> chain(currentDropPoint,dropPoints.get(0)));

		if(dropPoints.size() > 0) {
			currentDropPoint = dropPoints.get(0);
		}
	}

	public Map<String, Object> getGlobalDeliveriesDescription() throws IllegalAccessException {
		Map<String, Object> result = new HashMap<>();

		result.put("gone", this.getDroneGone());
		result.put("pending", this.getCurrentDeliveriesDescription());

		return result;
	}

	public GoToStep start() {
		started = true;
		return this.currentDropPoint.start();
	}

	public DropPoint truckDriverIsArrivedAtLocation() throws Exception {
		this.currentDropPoint.arrivedAtLocation();
		return this.currentDropPoint;
	}

	public List<Drone> getDroneGone() {
		return this.currentDropPoint.getDroneGone();
	}

	public Map<Drone, Delivery> getCurrentDeliveriesDescription() throws IllegalAccessException {
		if(!started) {
			throw new IllegalAccessException("Can not get current deliveries when tour has not started");
		}
		return this.currentDropPoint.getCurrentDeliveriesDescription();
	}

	private void chain(DropPoint dropPoint, DropPoint headOfChain) {
		DropPoint currentDropPoint = headOfChain;

		while(headOfChain.hasNext()) {
			currentDropPoint = currentDropPoint.next();
		}

		currentDropPoint.setNext(dropPoint);
	}

	public boolean checkAssociation(DroneID droneID, DeliveryID deliveryID) {
		return this.currentDropPoint.checkAssociation(droneID,deliveryID);
	}

	public DropPoint getCurrentDropPoint() {
		return currentDropPoint;
	}

	public void droneGone(DroneID droneID) {
		this.currentDropPoint.isGone(droneID);
	}

	public void droneBack(DroneID droneID) {
		this.currentDropPoint.declareIsBack(droneID);
	}
}

