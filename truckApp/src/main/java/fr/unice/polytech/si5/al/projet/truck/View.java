package fr.unice.polytech.si5.al.projet.truck;

/**
 * Created by Benjamin on 06/11/2015.
 */
public interface View {
	void tourHasStarted(GoToStep goToStep);
	void displayTourState();

	void displayDropPoint(DropPoint dropPoint);

	void refuseAssociation();

	void askIfDroneGone(String droneID);
}
