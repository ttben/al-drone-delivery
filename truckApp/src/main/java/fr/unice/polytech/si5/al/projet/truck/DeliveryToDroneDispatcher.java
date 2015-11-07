package fr.unice.polytech.si5.al.projet.truck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Benjamin Benni, Etienne Strobbe
 */
public class DeliveryToDroneDispatcher {


	public static Map<DroneID, Delivery> dispatch(List<Delivery> deliveries) {
		Map<DroneID, Delivery> result = new HashMap<>();
		return result;
	}

	public List<Drone> dispatch(Map<Drone, List<Delivery>> droneAssociation){
		return new ArrayList<>();
	}

	/**
	 * After a dead drone is declared, deliveries left must be redispatch
	 * @param deliveriesToDispatch
	 * @param droneAltAssociation
	 * @return List of deliveries that did not found a alt drone
	 */
	public static List<Delivery> dispatchAfterDeadDrone(List<Delivery> deliveriesToDispatch, Map<Delivery, List<Drone>> droneAltAssociation) {
		List<Delivery> failedDelivery = new ArrayList<>();
		for (Delivery delivery : deliveriesToDispatch){
			List<Drone> droneAlt = droneAltAssociation.get(delivery);
			boolean replacementFound = false;
			for (Drone drone : droneAlt){
				if (drone.isAlive()){
					drone.addDelivery(delivery);
					replacementFound = true;
					break;
				}
			}
			if(!replacementFound) {
				failedDelivery.add(delivery);
			}
		}
		return failedDelivery;
	}

	/*private static void chain(Delivery delivery, Delivery headOfChain) {
		Delivery currentDelivery = headOfChain;

		while(headOfChain.hasNext()) {
			currentDelivery = currentDelivery.next();
		}

		currentDelivery.setNext(delivery);
	}*/
}
