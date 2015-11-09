package fr.unice.polytech.si5.al.projet.truck;

import fr.unice.polytech.si5.al.projet.truck.assembly.DataBaseCreator;
import fr.unice.polytech.si5.al.projet.truck.assembly.template.TemplateDeliveryJSON;
import fr.unice.polytech.si5.al.projet.truck.assembly.template.TemplateDropPointJSON;

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
    public static Map<Delivery, List<Drone>> dispatchAltDroneToDeliveryFromTemplate(TemplateDropPointJSON aTemplateDropPointJson) {
        Map<Delivery,List<Drone>> mapToReturn = new HashMap<>();
        for (TemplateDeliveryJSON aTemplateDeliveryJson : aTemplateDropPointJson.getDeliveries()){
            // TODO change this Delivery into DeliveryID when Ben will push his changes
            Delivery delivery = DataBaseCreator.getDeliveryFromID(aTemplateDeliveryJson.getBox());
            List<Drone> drones = aTemplateDeliveryJson.getDroneAlt().stream().map(DataBaseCreator::getDroneFromID).collect(Collectors.toList());
            mapToReturn.put(delivery,drones);
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
