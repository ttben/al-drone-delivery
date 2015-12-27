package fr.unice.polytech.si5.al.projet.truck.domain.delivery.state;

class FailedDeliveryState extends DeliveryState {
	public String getName() {
		return "failed";
	}

	@Override
	public boolean isFailed() {
		return true;
	}
}
