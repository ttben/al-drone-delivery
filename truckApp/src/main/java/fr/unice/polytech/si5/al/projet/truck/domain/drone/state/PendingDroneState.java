package fr.unice.polytech.si5.al.projet.truck.domain.drone.state;

import fr.unice.polytech.si5.al.projet.truck.domain.drone.Drone;

class PendingDroneState extends DroneState {
	public PendingDroneState(Drone drone) {
		this.drone = drone;
	}

	@Override
	public String getName() {
		return "pending";
	}

	@Override
	public boolean isPending() {
		return true;
	}

	@Override
	public DroneState fly() {
		this.drone.getCurrentDelivery().ship();

		return new FlyDroneState(this.drone);
	}

	@Override
	public DroneState declareFailure() {
		this.drone.flushAllDeliveries();
		return new FailureDeclaredDroneState();
	}
}