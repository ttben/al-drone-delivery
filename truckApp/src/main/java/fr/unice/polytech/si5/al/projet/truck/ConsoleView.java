package fr.unice.polytech.si5.al.projet.truck;

import fr.unice.polytech.si5.al.projet.truck.domain.DropPoint;
import fr.unice.polytech.si5.al.projet.truck.domain.GoToStep;
import fr.unice.polytech.si5.al.projet.truck.domain.delivery.Delivery;
import fr.unice.polytech.si5.al.projet.truck.domain.drone.Drone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

/**
 * Created by Benjamin on 06/11/2015.
 */
public class ConsoleView implements View {
	private Controller controller;

	public ConsoleView(Controller controller) {
		this.controller = controller;

		System.out.println("\t====================================\n\t\tBienvenue sur l'application !\n\t====================================\n");
	}

	public void tourHasStarted(GoToStep goToStep) {
		System.out.println("+ Rendez-vous à " + goToStep.getDescription());
		boolean arrived = false;
		do {
			System.out.println("+ Etez-vous arrive ..? (Y/N)");
			arrived = this.readYesNoInput();
		}
		while (!arrived);

		this.controller.isArrivedAtLocation();
	}

	public void displayTourState(Map<String, Object> globalContext) {


		globalContext.forEach((status, droneDeliveryMap) -> {
			switch (status) {
				case "fly":
					this.displayGoneDrones((List<Drone>) droneDeliveryMap);
					break;
				case "pending":
					this.displayPendingDrones((Map<Drone, Delivery>) droneDeliveryMap);
					break;
				default:
					break;
			}
		});

		boolean validAction;
		String action;

		do {
			System.out.println("\nQue souhaitez vous faire ?");
			System.out.println("\t1.\tEffectuer une autre livraison");
			System.out.println("\t2.\tDeclarer qu'un drone est revenu");

			action = readInput();

			validAction = "1".equalsIgnoreCase(action) || "2".equalsIgnoreCase(action);

		} while (!validAction);


		switch (action) {
			case "1":
				doShipping();
				break;
			case "2":
				declareDroneCameBack();
				break;
			default:
				break;
		}

	}

	@Override
	public void displayDropPoint(DropPoint dropPoint) {
		System.out.println(dropPoint.getCurrentDeliveriesDescription());


	}

	public void doShipping() {
		System.out.println("Quel numero de drone ?");
		String droneID = readInput();

		System.out.println("Et quel paquet ?");
		String packageID = readInput();

		this.controller.checkPair(droneID, packageID);
	}

	public void declareDroneCameBack() {
		System.out.println("Quel drone est revenu ?");
		String droneID = readInput();

		try {
			this.controller.droneBack(droneID);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void displayPendingDrones(Map<Drone, Delivery> droneDeliveryMap) {
		System.out.println("\n---------------------------------------------------------");
		System.out.println("\t\t\t\t\t+ Drones a envoyer +\n");
		droneDeliveryMap.forEach((drone, delivery) -> {
			System.out.println("DEBUG : DR/DE : " + drone + " " + delivery );
			System.out.println("\t--> Attachez le drone #" + drone.getID().getValue() + " (" + drone.getName() + ") au paquet #" + delivery.getID().getValue());
		});
		System.out.println("\n---------------------------------------------------------");

	}

	private void displayGoneDrones(List<Drone> droneDeliveryMap) {
		System.out.println("\n---------------------------------------------------------");
		System.out.println("\t\t\t\t\t+ Drones en vol +\n");
		droneDeliveryMap.forEach((drone) -> {
			System.out.println("\t--> Drone #" + drone.getID().getValue() + " (" + drone.getName() + ")");
		});
		System.out.println("---------------------------------------------------------");

	}

	@Override
	public void refuseAssociation() {
		System.out.println("Association refusee : mauvaise pair drone/package");
	}

	@Override
	public void askIfDroneGone(String droneID) {
		System.out.println("Le drone est parti sans probleme ? (Y/N)");
		boolean isGoneCorrectly = this.readYesNoInput();
		if (isGoneCorrectly) {
			this.controller.droneGone(droneID);
		} else {
			this.controller.declareDroneProblem(droneID);
		}
	}

	@Override
	public void displayStartTour() {
		System.out.println("Vous n'avez pas encore commence cette tournee ... On y Go ? (Y/N)");

		if (this.readYesNoInput()) {
			controller.startTour();
		}
	}

	@Override
	public void displayDroneNotFound(String droneID) {
		System.out.println("Le drone de numero " + droneID + " n'a pas ete trouve");
	}

	@Override
	public void displayTourFinished() {
		System.out.println("C'est fini !!!!");
	}


	private String readInput() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = "";
		try {
			s = br.readLine();

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return s;
	}

	private boolean readYesNoInput() {
		return ("Y".equalsIgnoreCase(readInput()));
	}
}
