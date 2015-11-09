package fr.unice.polytech.si5.al.projet.truck;

import fr.unice.polytech.si5.al.projet.truck.assembly.DataBaseCreator;
import fr.unice.polytech.si5.al.projet.truck.assembly.template.TemplateDeliveryJSON;
import fr.unice.polytech.si5.al.projet.truck.assembly.template.TemplateDropPointJSON;
import fr.unice.polytech.si5.al.projet.truck.domain.DropPoint;
import fr.unice.polytech.si5.al.projet.truck.domain.delivery.Delivery;
import fr.unice.polytech.si5.al.projet.truck.domain.delivery.DeliveryID;
import fr.unice.polytech.si5.al.projet.truck.domain.drone.Drone;
import fr.unice.polytech.si5.al.projet.truck.domain.drone.DroneID;

import java.util.*;
import java.util.stream.Collectors;

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
	public static List<Drone> dispatchAfterDeadDrone(List<Delivery> deliveriesToDispatch, Map<DeliveryID, List<Drone>> droneAltAssociation) {
		System.out.println("DELIVERIES TO DISPATCH : " + deliveriesToDispatch + " DRONE ALT ASSOC " + droneAltAssociation);

		List<Delivery> failedDelivery = new ArrayList<>();
		List<Drone> dronesUsed = new ArrayList<>();

		for (Delivery delivery : deliveriesToDispatch){
			List<Drone> droneAlt = droneAltAssociation.get(delivery.getID());
			boolean replacementFound = false;
			for (Drone drone : droneAlt){
				if (drone.isAlive()){

					drone.addDelivery(delivery);
					replacementFound = true;

					if(!dronesUsed.contains(drone)) {
						dronesUsed.add(drone);
					}

					break;
				}

			}
			if(!replacementFound) {
				failedDelivery.add(delivery);
				delivery.fail();
			}
		}
		return dronesUsed;
	}

    /**
     * Build a list of Drone from a Template (built from a JSON file)
     * Each drone contains the list of Delivery that it's supposed to deliver.
     * @param aTemplateDropPointJson the template
     * @return the list of Drone
     */
	public static List<Drone> dispatchDeliveryToDroneFromTemplate(TemplateDropPointJSON aTemplateDropPointJson) {
		Set<Drone> drones = new HashSet<>();
		for(TemplateDeliveryJSON aTemplateDeliveryJSON : aTemplateDropPointJson.getDeliveries()){
            Drone theDrone = DataBaseCreator.getDroneFromID(aTemplateDeliveryJSON.getDrone());
            theDrone.addDelivery(DataBaseCreator.getDeliveryFromID(aTemplateDeliveryJSON.getBox()));
			drones.add(theDrone);
		}
        return new ArrayList<>(drones);
	}

    /**
     * Build the map associating Delivery to alternatives drones from a template (built from a JSON file)
     * @param aTemplateDropPointJson the template
     * @return the map containing the deliveries and theirs alt drones
     */
    public static Map<DeliveryID, List<Drone>> dispatchAltDroneToDeliveryFromTemplate(TemplateDropPointJSON aTemplateDropPointJson) {
        Map<DeliveryID,List<Drone>> mapToReturn = new HashMap<>();
        for (TemplateDeliveryJSON aTemplateDeliveryJson : aTemplateDropPointJson.getDeliveries()){
            Delivery delivery = DataBaseCreator.getDeliveryFromID(aTemplateDeliveryJson.getBox());
            List<Drone> drones = aTemplateDeliveryJson.getDroneAlt().stream().map(DataBaseCreator::getDroneFromID).collect(Collectors.toList());
            mapToReturn.put(delivery.getID(),drones);
        }
        return mapToReturn;
    }

	public static void chain(DropPoint delivery, DropPoint headOfChain) {
        DropPoint currentDelivery = headOfChain;

		while(headOfChain.hasNext()) {
			currentDelivery = currentDelivery.next();
		}

		currentDelivery.setNext(delivery);
	}
}
