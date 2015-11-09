package fr.unice.polytech.si5.al.projet.truck;

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

		System.out.println("Bienvenue sur l'application !");
	}

	public void tourHasStarted(GoToStep goToStep) {
		System.out.println("-> Tournee demarree");
		System.out.println("Rendez-vous à " + goToStep.getDescription());

		System.out.println("Arrive ..? (Y/N)");
		this.readYesNoInput();
		this.controller.isArrivedAtLocation();
	}

	public void displayTourState() {
		System.out.println("Tournee courante :");
		Map<String, Object> globalContext = null;
		try {
			globalContext = controller.getGlobalTourDescription();

			globalContext.forEach((status, droneDeliveryMap) -> {
				switch (status) {
					case "gone":
						this.displayGoneDrones((List<Drone>) droneDeliveryMap);
						break;
					case "pending":
						this.displayPendingDrones((Map<Drone, Delivery>) droneDeliveryMap);
						break;
					default:
						break;
				}
			});

			boolean actionValide = true;
			String action = "";

			do {
				System.out.println("Que souhaitez vous faire ?");
				System.out.println("1. Effectuer une autre livraison");
				System.out.println("2. Declarer qu'un drone est revenu");

				action = readInput();

				actionValide = "1".equalsIgnoreCase(action) || "2".equalsIgnoreCase(action);
				System.out.println(action + " walide? " + actionValide);

			} while (!actionValide);


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

		} catch (IllegalAccessException e) {
			System.out.println("Vous n'avez pas encore commence cette tournee ... On y Go ? (Y/N)");

			if (this.readYesNoInput()) {
				controller.startTour();
			}
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
		System.out.println("Drones a envoyer");
		droneDeliveryMap.forEach((drone, delivery) -> {
			System.out.println("\t+ Attachez le drone #" + drone.getID().getValue() + " (" + drone.getName() + ") au paquet #" + delivery.getID().getValue());
		});
	}

	private void displayGoneDrones(List<Drone> droneDeliveryMap) {
		System.out.println("Drones en vol");
		droneDeliveryMap.forEach((drone) -> {
			System.out.println("\t+ Drone #" + drone.getID().getValue() + " (" + drone.getName() + ")");
		});
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
			this.controller.declareDroneProblem();
		}
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
