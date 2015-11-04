package fr.unice.polytech.si5.al.projet.s3.truck.step;

import fr.unice.polytech.si5.al.projet.s3.truck.DeliveryID;
import fr.unice.polytech.si5.al.projet.s3.truck.DeliveryToDroneDispatcher;
import fr.unice.polytech.si5.al.projet.s3.truck.DroneID;

import java.util.*;

public class Deployment {

	private Deployment prev;
	private Deployment next;

	private Map<DroneID, Delivery> mapDroneIDToCurrentDelivery = new HashMap<>();

	public Deployment(List<Delivery> deliveries) {
		mapDroneIDToCurrentDelivery = DeliveryToDroneDispatcher.dispatch(deliveries);
	}

	public void start(DeliveryID deliveryID) {
		mapDroneIDToCurrentDelivery.values().stream().filter(delivery -> delivery.getID() == deliveryID).forEach(delivery -> delivery.start());
	}

	public boolean checkDelivery(DroneID droneID, String boxID) {
		//	TODO
		//	delivery.check(droneID, boxid)
		//
		return true;
	}

	public void isGone(DroneID droneID) {
		Delivery deliveryTarget = this.mapDroneIDToCurrentDelivery.get(droneID);

		if(!deliveryTarget.isValidated()) {
			throw new IllegalArgumentException("Delivery declared gone but was " + deliveryTarget.getStatus());
		}

		deliveryTarget.doing();
	}

	public void isBack(DroneID droneID) {
		Delivery deliveryTarget = this.mapDroneIDToCurrentDelivery.get(droneID);

		if(deliveryTarget == null) {
			throw new IllegalArgumentException("Specified drone was not found : " + droneID);
		}

		if(!deliveryTarget.isDoing()) {
			throw new IllegalArgumentException("Delivery declared back but were never declared gone before. Was " + deliveryTarget.getStatus());
		}

		deliveryTarget.done();

		if(deliveryTarget.hasNext()) {
			Delivery nextDelivery = deliveryTarget.next();
			mapDroneIDToCurrentDelivery.put(droneID, nextDelivery);
		}
		else {
			mapDroneIDToCurrentDelivery.remove(droneID);
		}
	}

	public List<Delivery> getCurrentDeliveries() {
		return new ArrayList<>(mapDroneIDToCurrentDelivery.values());
	}

	public void setPrev(Deployment prev) {
		this.prev = prev;
	}

	public void setNext(Deployment next) {
		this.next = next;
	}

	public boolean hasPrev() {
		return this.prev != null;
	}

	public boolean hasNext() {
		return this.next != null;
	}

	public Deployment next() {
		return this.next;
	}

	public void detach() {
		this.prev = null;
		this.next = null;
	}
}