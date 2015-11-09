package fr.unice.polytech.si5.al.projet.truck.domain.delivery.state;

class StartDeliveryState extends DeliveryState {
	public String getName() {
		return "started";
	}

	@Override
	public DeliveryState ship() {
		return new ShippingDeliveryState();
	}

	@Override
	public boolean hasStarted() {
		return true;
	}
}