package fr.unice.polytech.si5.al.projet.truck.domain.delivery.state;

class DoneDeliveryState extends DeliveryState {
	public String getName() {
		return "done";
	}

	@Override
	public boolean isDone() {
		return true;
	}
}
