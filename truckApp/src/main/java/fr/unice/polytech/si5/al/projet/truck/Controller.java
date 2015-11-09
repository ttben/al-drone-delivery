package fr.unice.polytech.si5.al.projet.truck;

import java.util.*;

/**
 * Created by Benjamin on 06/11/2015.
 */
public class Controller {
	private Tour model;
	private View view;

	public Controller() {
		/*
		List<Drone> drones = new ArrayList<>();
		Drone packito = new Drone("7","Packito");
		Drone geraldo = new Drone("3", "Geraldo");

		Delivery delivery1OfPackito = new Delivery(new DeliveryID("delivery1"), "livraison chez monsieur duval", new Box("-82.55N 78.79E",3.0));
		Delivery delivery2OfPackito = new Delivery(new DeliveryID("delivery2"), "livraison chez monsieur strobbe", new Box("-82.50N 77.19E",1.0));

		Delivery delivery1OfGeraldo = new Delivery(new DeliveryID("delivery3"), "livraison chez moi", new Box("-81.55N 75.79E",1.5));

		packito.addDelivery(delivery1OfPackito);
		packito.addDelivery(delivery2OfPackito);

		geraldo.addDelivery(delivery1OfGeraldo);

		drones.add(packito);
		drones.add(geraldo);

		Map<Delivery, List<Drone>> altDrones = new HashMap<>();

		GoToStep goToStep = new GoToStep("Super U", "-82.5588N 78.787E");
		Deployment deployment = new Deployment(drones, altDrones);

		DropPoint dp = new DropPoint(goToStep, deployment);
		List<DropPoint> dps = new ArrayList<>();
		dps.add(dp);

		this.model = new Tour(dps);
		this.view = new ConsoleView(this);
		this.view.displayTourState();
		*/
	}

	public Map<String, Object> getGlobalTourDescription() throws IllegalAccessException {
		return this.model.getGlobalDeliveriesDescription();
	}

	public void startTour() {
		GoToStep goToStep = model.start();
		this.view.tourHasStarted(goToStep);
	}

	public Map<Drone, Delivery> getCurrentDeliveriesDescription() throws IllegalAccessException {
		return this.model.getCurrentDeliveriesDescription();
	}

	public static void main(String[] args) {
		Controller controller = new Controller();

	}

	public void isArrivedAtLocation() {
		DropPoint dropPoint = null;
		try {
			dropPoint = this.model.truckDriverIsArrivedAtLocation();
		} catch (Exception e) {
			// TODO display error
			e.printStackTrace();
		}
		this.view.displayTourState();
	}

	public void checkPair(String droneID, String packageID) {
		try {
			if(this.model.checkAssociation(new DroneID(droneID), new DeliveryID(packageID))) {
				this.view.askIfDroneGone(droneID);
			}
		}
		catch (Exception e) {
			this.view.refuseAssociation();
			this.view.displayDropPoint(this.model.getCurrentDropPoint());
		}
	}

	public void declareDroneProblem() {

	}

	public void droneGone(String droneID) {
		this.model.droneGone(new DroneID(droneID));
		this.view.displayTourState();
	}

	public void droneBack(String droneID) {
		this.model.droneBack(new DroneID(droneID));
		this.view.displayTourState();
	}

}
