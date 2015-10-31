package fr.unice.polytech.si5.al.projet.s3.truck;

public class DroneDeliveryApp {
	private Tour tour;

	public DroneDeliveryApp(Tour tour) {
		this.tour = tour;
	}

	public void start() {
		while(!this.tour.isDone()) {
			tour.execute();
		}
	}
}