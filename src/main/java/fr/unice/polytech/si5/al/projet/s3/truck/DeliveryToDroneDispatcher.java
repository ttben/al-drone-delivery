package fr.unice.polytech.si5.al.projet.s3.truck;

import fr.unice.polytech.si5.al.projet.s3.truck.step.Delivery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Benjamin on 04/11/2015.
 */
public class DeliveryToDroneDispatcher {
	public static Map<DroneID, Delivery> dispatch(List<Delivery> deliveries) {
		Map<DroneID, Delivery> result = new HashMap<>();

		deliveries.forEach(currentDelivery -> {

			DroneID currentDroneID = currentDelivery.getDroneID();

			//	Check if drone of current delivery has already been added
			if (result.keySet().contains(currentDroneID)) {
				//	Retrieve the head of the existing chain
				Delivery existingDelivery = result.get(currentDroneID);

				//	Chain the current delivery at the end of the existing chain
				chain(currentDelivery, existingDelivery);
			} else {
				result.put(currentDroneID, currentDelivery);
			}
		});

		return result;
	}

	private static void chain(Delivery delivery, Delivery headOfChain) {
		Delivery currentDelivery = headOfChain;

		while(headOfChain.hasNext()) {
			currentDelivery = currentDelivery.next();
		}

		currentDelivery.setNext(delivery);
	}
}
