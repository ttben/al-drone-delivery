package fr.unice.polytech.si5.al.projet.truck;

import java.util.*;

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
	private Map<Delivery, List<Drone>> droneAltAssociation;

	public Deployment(List<Drone> drones, Map<Delivery, List<Drone>> droneAltAssociation) {
		this.drones = drones;
		this.droneAltAssociation = droneAltAssociation;
	}

	public Map<Drone, List<Delivery>> getDeliveriesDescription() {
		Map<Drone, List<Delivery>> result = new HashMap<>();

		drones.forEach(drone -> {
			result.put(drone, drone.getDeliveries());
		});

		return result;
	}

	/**
	 * Start a delivery in the deployment
	 * @param deliveryID the delivery to start
	 */
	public void start(DeliveryID deliveryID) {
		drones.forEach(Drone::startNextDelivery);

		List<Delivery> currentDeliveries = this.getCurrentDeliveries();
		Delivery targetDelivery = currentDeliveries.stream().filter(delivery -> delivery.getID().equals(deliveryID)).findFirst().get();
		targetDelivery.start();
	}

	/**
	 * Declare that a drone is gone to deliver a package (box)
	 * Can throw an IllegalArgumentException if drone is not found
	 * @param droneID the drone that's gone
	 */
	public void isGone(DroneID droneID) {
		Drone drone = getDroneFromDroneID(droneID);

		if(drone == null) {
			throw new IllegalArgumentException("Specified drone was not found : " + droneID);
		}
		drone.startNextDelivery();
	}

	/**
	 * Look for a Drone in the drones by its DroneID
	 * @param droneID
	 * @return the drone if found, null otherwise
	 */
	private Drone getDroneFromDroneID(DroneID droneID){
		for(Drone drone : drones){
			if(drone.getID().equals(droneID)){
				return drone;
			}
		}
		return null;
	}

	/**
	 * Declare that a drone is dead (for any reason)
	 * @param droneID the drone that's dead
	 */
	public void isDead(DroneID droneID) {
		Drone drone = getDroneFromDroneID(droneID);
		if(drone == null) {
			throw new IllegalArgumentException("Specified drone was not found : " + droneID);
		}

		List<Delivery> deliveriesToDispatch = drone.declareIsDead();
		DeliveryToDroneDispatcher.dispatchAfterDeadDrone(deliveriesToDispatch,droneAltAssociation);
	}

	/**
	 * Declare that a drone is back from a delivery
	 * Can throw an IllegalArgumentException if not declared gone before or unknown DroneID
	 * @param droneID the drone that's back
	 */
	public void isBack(DroneID droneID) {
		Drone drone = getDroneFromDroneID(droneID);

		if (drone == null) {
			throw new IllegalArgumentException("Specified drone was not found : " + droneID);
		}

		drone.isBack();

	}

	/**
	 * Give the state of the deployment : done or not
	 * @return true if done, false otherwise
	 */
	public boolean isDone() {

		for(Drone drone : drones) {
			if(drone.getRemainingDeliveries().size() > 0) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Get the list of current deliveries
	 * (a current delivery is a delivery that's being at least processed)
	 * @return the list of current deliveries
	 */
	public List<Delivery> getCurrentDeliveries() {
		List<Delivery> listToReturn = new ArrayList<>();
		for (Drone drone : drones){
			Delivery delivery = drone.getCurrentDelivery();
			if (delivery != null) {
				listToReturn.add(delivery);
			}
		}
		return listToReturn;
	}

}