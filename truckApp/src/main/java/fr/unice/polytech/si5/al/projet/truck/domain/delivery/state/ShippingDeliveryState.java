package fr.unice.polytech.si5.al.projet.truck.domain.delivery.state;

class ShippingDeliveryState extends DeliveryState {
	public String getName() {
		return "being shipped";
	}

	@Override
	public DeliveryState done() {
		return new DoneDeliveryState();
	}

	@Override
	public DeliveryState fail() {
		return new FailedDeliveryState();
	}

	@Override
	public boolean isBeingShipped() {
		return true;
	}
}