package fr.unice.polytech.si5.al.projet.s3.truck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class represent a drone deployment<br/>
 * It handles deployment and responds at the following question :
 * <ul>
 *     <li>Is a drone gone ?</li>
 *     <li>Is a drone back ?</li>
 *     <li>Is a drone dead ?</li>
 *     <li>Has a deployment started ?</li>
 *     <li>Is a deployment done ?</li>
 * </ul>
 */
public class Deployment {

	private List<Drone> drones = new ArrayList<>();
	private Map<DeliveryID, List<Drone>> mapDeliveryIDToAltDrones = new HashMap<>();

	private Map<DroneID, Delivery> mapDroneIDToCurrentDelivery = new HashMap<>();

	public Deployment(List<Delivery> deliveries) {
		mapDroneIDToCurrentDelivery = DeliveryToDroneDispatcher.dispatch(deliveries);
	}

	public Map<Drone, List<Delivery>> getDeliveriesDescription() {
		Map<Drone, List<Delivery>> result = new HashMap<>();

		drones.forEach(drone -> {
			result.put(drone, drone.getDeliveries());
		});

		return result;
	}

	public void start(DeliveryID deliveryID) {
		List<Delivery> currentDeliveries = this.getCurrentDeliveries();
		Delivery targetDelivery = currentDeliveries.stream().filter(delivery -> delivery.getID().equals(deliveryID)).findFirst().get();
		targetDelivery.start();
	}

	public void isGone(DroneID droneID) {
		Delivery deliveryTarget = this.mapDroneIDToCurrentDelivery.get(droneID);

		if(!deliveryTarget.isValidated()) {
			throw new IllegalArgumentException("Delivery declared gone but was " + deliveryTarget.getState());
		}

		deliveryTarget.doing();
	}

	public void isDead(DroneID droneID) {

		//	Set all delivery state at "FAILED"
		Delivery remainingChainedDeliveries = this.mapDroneIDToCurrentDelivery.get(droneID);
		Delivery currentDelivery = remainingChainedDeliveries;
		while(currentDelivery.hasNext()) {
			currentDelivery.fail();
			currentDelivery = currentDelivery.next();
		}
	}

	public void isBack(DroneID droneID) {
		Delivery deliveryTarget = this.mapDroneIDToCurrentDelivery.get(droneID);

		if(deliveryTarget == null) {
			throw new IllegalArgumentException("Specified drone was not found : " + droneID);
		}

		if(!deliveryTarget.isDoing()) {
			throw new IllegalArgumentException("Delivery declared back but were never declared gone before. Was " + deliveryTarget.getState());
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

	public boolean isDone() {

		for(DroneID droneID : mapDroneIDToCurrentDelivery.keySet()) {
			if(mapDroneIDToCurrentDelivery.get(droneID).hasNext()) {
				return false;
			}
		}

		return true;
	}

	public List<Delivery> getCurrentDeliveries() {
		return new ArrayList<>(mapDroneIDToCurrentDelivery.values());
	}

}