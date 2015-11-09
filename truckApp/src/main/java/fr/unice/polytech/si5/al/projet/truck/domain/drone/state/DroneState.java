package fr.unice.polytech.si5.al.projet.truck.domain.drone.state;

import fr.unice.polytech.si5.al.projet.truck.domain.drone.Drone;

public abstract class DroneState {
	protected Drone drone;

	public DroneState pending() {
		throw new UnsupportedOperationException("Drone can not fly in state : " + this.getName());
	}

	public DroneState fly() {
		throw new UnsupportedOperationException("Drone can not fly in state : " + this.getName());
	}

	public DroneState collect() {
		throw new UnsupportedOperationException("Drone can not be collected in state : " + this.getName());
	}

	public DroneState declareFailure() {
		throw new UnsupportedOperationException("Drone can not be declared with failure in state : " + this.getName());
	}

	public boolean isPending() {
		return false;
	}

	public boolean isFlying() {
		return false;
	}

	public boolean isGetBack() {
		return false;
	}

	public boolean isInFailure() {
		return false;
	}

	public boolean isAlive() {
		return !isInFailure();
	}

	public static DroneState getDefaultState(Drone drone) {
		return new PendingDroneState(drone);
	}

	public abstract String getName();
}