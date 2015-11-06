package fr.unice.polytech.si5.al.projet.truck;

import java.util.List;

/**
 * Created by Benjamin on 06/11/2015.
 */
public class ConsoleView implements View {
	private Tour controller;

	public ConsoleView() {
		System.out.println("Bienvenue sur l'application !");
	}


	private void displayTourState() {
		System.out.println("Tournee courante :");
		List<Delivery> currentDeliveries = controller.getCurrentDeliveries();

		currentDeliveries.forEach(delivery -> {
			System.out.println("");
		});
	}

}
