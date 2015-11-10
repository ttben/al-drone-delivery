package fr.unice.polytech.si5.al.projet.truck.domain.delivery.state;

import fr.unice.polytech.si5.al.projet.truck.domain.delivery.Delivery;

public abstract class DeliveryState {
	protected Delivery delivery;

	public DeliveryState putOnHold() {
		throw new UnsupportedOperationException("Delivery can not be put on hold in current state " + this.getName());
	}

	public DeliveryState start() {
		throw new UnsupportedOperationException("Delivery can not be started in current state " + this.getName());
	}

	public DeliveryState ship() {
		throw new UnsupportedOperationException("Delivery can not be shipped in current state " + this.getName());
	}

	public DeliveryState done() {
		throw new UnsupportedOperationException("Delivery can not be done in current state " + this.getName());
	}

	public DeliveryState fail() {
		throw new UnsupportedOperationException("Delivery can not be failed in current state " + this.getName());
	}

	public boolean isPending() {
		return false;
	}

	public boolean hasStarted() {
		return false;
	}

	public boolean isBeingShipped() {
		return false;
	}

	public boolean isDone() {
		return false;
	}

	public boolean isFailed() {
		return false;
	}

	public static DeliveryState getDefaultState() {
		return new PutOnHoldDeliveryState();
	}

	public abstract String getName();
}