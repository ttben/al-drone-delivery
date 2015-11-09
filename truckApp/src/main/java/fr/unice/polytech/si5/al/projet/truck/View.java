package fr.unice.polytech.si5.al.projet.truck;

import fr.unice.polytech.si5.al.projet.truck.domain.DropPoint;
import fr.unice.polytech.si5.al.projet.truck.domain.GoToStep;

import java.util.Map;

/**
 * Created by Benjamin on 06/11/2015.
 */
public interface View {
	void tourHasStarted(GoToStep goToStep);
	void displayTourState(Map<String, Object> globalTourDescription);

	void displayDropPoint(DropPoint dropPoint);

	void refuseAssociation();

	void askIfDroneGone(String droneID);

	void displayStartTour();

	void displayDroneNotFound(String droneID);
}
