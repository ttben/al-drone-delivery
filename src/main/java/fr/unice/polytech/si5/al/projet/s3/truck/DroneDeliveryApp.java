package fr.unice.polytech.si5.al.projet.s3.truck;

public class DroneDeliveryApp {
	private Node tour;

	public DroneDeliveryApp(Node tour) {
		this.tour = tour;
	}

	public void start() {
		while(!this.tour.isDone()) {
			tour.doNext();
		}
	}
}