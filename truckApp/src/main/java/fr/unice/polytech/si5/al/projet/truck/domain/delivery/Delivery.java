package fr.unice.polytech.si5.al.projet.truck.domain.delivery;


import fr.unice.polytech.si5.al.projet.truck.domain.State;
import fr.unice.polytech.si5.al.projet.truck.domain.delivery.state.DeliveryState;

public class Delivery{
	private DeliveryState deliveryState;

	private DeliveryID ID;
	private String destination;

	protected State state;

	public Delivery(DeliveryID ID, String destination) {
		this.ID = ID;
		this.destination = destination;
        this.deliveryState = DeliveryState.getDefaultState();
	}

	public DeliveryID getID() {
		return this.ID;
	}

	public DeliveryState getState() {
		return deliveryState;
	}

	public void start() {
		this.deliveryState = this.deliveryState.start();
	}

	public void ship() {
		this.deliveryState = this.deliveryState.ship();
	}

	public void done() {
		this.deliveryState = this.deliveryState.done();
	}

	public void fail() {
		this.deliveryState = this.deliveryState.fail();
	}

	public boolean isPending() {
		return this.deliveryState.isPending();
	}

	public boolean isBeingShipped() {
		return this.deliveryState.isBeingShipped();
	}

	public boolean isDone() {
		return this.deliveryState.isDone();
	}

	public boolean isFailed() {
		return this.deliveryState.isFailed();
	}

	@Override
	public String toString() {
		return this.ID.toString();
	}
}