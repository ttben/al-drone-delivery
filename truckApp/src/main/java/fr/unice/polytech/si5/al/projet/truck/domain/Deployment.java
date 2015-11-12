package fr.unice.polytech.si5.al.projet.truck.domain;

import fr.unice.polytech.si5.al.projet.truck.DeliveryToDroneDispatcher;
import fr.unice.polytech.si5.al.projet.truck.domain.delivery.Delivery;
import fr.unice.polytech.si5.al.projet.truck.domain.delivery.DeliveryID;
import fr.unice.polytech.si5.al.projet.truck.domain.drone.Drone;
import fr.unice.polytech.si5.al.projet.truck.domain.drone.DroneID;

import java.util.*;

/**
 * This class represent a drone deployment<br/>
 * It handles deployment and responds at the following question :
 * <ul>
 *     <li>Is a drone fly ?</li>
 *     <li>Is a drone back ?</li>
 *     <li>Is a drone dead ?</li>
 *     <li>Has a deployment started ?</li>
 *     <li>Is a deployment done ?</li>
 * </ul>
 */
public class Deployment {

	private List<Drone> drones = new ArrayList<>();
	private Map<DeliveryID, List<Drone>> droneAltAssociation;

	public Deployment(List<Drone> drones, Map<DeliveryID, List<Drone>> droneAltAssociation) {
		this.drones = drones;
		this.droneAltAssociation = droneAltAssociation;
	}

	public Map<Drone, Delivery> getCurrentDeliveriesDescription() {
		 Map<Drone, Delivery> result = new HashMap<>();

		this.getDroneInTruck().forEach(drone -> {
			if(drone.hasDeliveriesLeft()) {
				result.put(drone, drone.getCurrentDelivery());
			}
		});

		return result;
	}

	public void start() {
		drones.forEach(drone -> {
			drone.startNextDelivery();
		});
	}



	/**
	 * Declare that a drone is fly to deliver a package (box)
	 * Can throw an IllegalArgumentException if drone is not found
	 * @param droneID the drone that's fly
	 */
	public void isGone(DroneID droneID) {
		Drone drone = getDroneFromDroneID(droneID);

		if(drone == null) {
			throw new IllegalArgumentException("Specified drone was not found : " + droneID);
		}
		drone.fly();
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
		List<Drone> dronesList = DeliveryToDroneDispatcher.dispatchAfterDeadDrone(deliveriesToDispatch,droneAltAssociation);

		for(Drone d : dronesList) {
			if(!this.drones.contains(d)) {
				this.drones.add(d);
			}
			try {
				d.startNextDelivery();
			}
			catch(Exception e) {}
		}

	}

	/**
	 * Declare that a drone is back from a delivery
	 * Can throw an IllegalArgumentException if not declared fly before or unknown DroneID
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
		System.out.println("Check if deployment is done ...\n" + this.drones);
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

	public boolean checkAssociation(DroneID droneID, DeliveryID deliveryID) {
		Drone drone = this.getDroneFromDroneID(droneID);
		if(drone == null) {
			throw new IllegalArgumentException("DroneID not found");
		}

		boolean isCurrentDroneDelivery = drone.getCurrentDelivery().getID().equals(deliveryID);
		if (!isCurrentDroneDelivery) {
			throw new IllegalArgumentException("DeliveryID is not delivery to do with specified drone");
		}

		return true;
	}

	public List<Drone> getDroneGone() {
		List<Drone> dronesGone = new ArrayList<>();

		drones.forEach(drone -> {
			if(drone.isGone()) {
				dronesGone.add(drone);
			}
		});
		return dronesGone;
	}

	public List<Drone> getDroneInTruck() {
		List<Drone> dronesPending = new ArrayList<>();

		drones.forEach(drone -> {
			if(drone.isAvailable()) {
				dronesPending.add(drone);
			}
		});

		return dronesPending;
	}

}