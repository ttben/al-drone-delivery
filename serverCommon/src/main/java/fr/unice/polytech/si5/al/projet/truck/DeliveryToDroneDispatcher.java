package fr.unice.polytech.si5.al.projet.truck;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Benjamin on 04/11/2015.
 */
public class DeliveryToDroneDispatcher {
	public static Map<DroneID, Delivery> dispatch(List<Delivery> deliveries) {
		Map<DroneID, Delivery> result = new HashMap<>();


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
