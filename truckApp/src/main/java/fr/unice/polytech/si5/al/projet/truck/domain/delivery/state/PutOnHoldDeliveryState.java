package fr.unice.polytech.si5.al.projet.truck.domain.delivery.state;

import fr.unice.polytech.si5.al.projet.truck.domain.delivery.Delivery;

class PutOnHoldDeliveryState extends DeliveryState {
	public String getName() {
		return "put on hold";
	}

	@Override
	public DeliveryState start() {
		return new StartDeliveryState();
	}

	@Override
	public boolean isPending() {
		return true;
	}
}
