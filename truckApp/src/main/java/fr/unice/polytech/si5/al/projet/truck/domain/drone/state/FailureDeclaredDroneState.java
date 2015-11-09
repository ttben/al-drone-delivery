package fr.unice.polytech.si5.al.projet.truck.domain.drone.state;

class FailureDeclaredDroneState extends DroneState {
	@Override
	public String getName() {
		return "in failure";
	}

	@Override
	public boolean isInFailure() {
		return true;
	}
}
