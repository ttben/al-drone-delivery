package fr.unice.polytech.si5.al.projet.truck.domain.drone.state;

import fr.unice.polytech.si5.al.projet.truck.domain.delivery.Delivery;
import fr.unice.polytech.si5.al.projet.truck.domain.drone.Drone;

class FlyDroneState extends DroneState {
	public FlyDroneState(Drone drone) {
		this.drone = drone;
	}

	@Override
	public String getName() {
		return "flying";
	}

	@Override
	public boolean isFlying() {
		return true;
	}

	@Override
	public DroneState collect() {

		Delivery currentDelivery = this.drone.getCurrentDelivery();

		if (currentDelivery == null) {
			throw new IllegalStateException("A delivery is back, but there is no current delivery for drone " + this.drone.getName());
		}

		if (!currentDelivery.isBeingShipped()) {
			throw new IllegalStateException("A delivery with status different from ship is declared as back");
		}

		currentDelivery.done();

		this.drone.addDoneDelivery(currentDelivery);
		this.drone.deleteCurrentDelivery();
		this.drone.startNextDelivery();

		return new PendingDroneState(this.drone);
	}

	@Override
	public DroneState declareFailure() {
		this.drone.flushAllDeliveries();
		return new FailureDeclaredDroneState();
	}
}