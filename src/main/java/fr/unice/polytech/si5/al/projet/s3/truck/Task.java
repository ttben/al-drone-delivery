package fr.unice.polytech.si5.al.projet.s3.truck;

import fr.unice.polytech.si5.al.projet.s3.drone.Drone;
import fr.unice.polytech.si5.al.projet.s3.shipping.Shipping;

import java.util.List;

public interface Task {
	boolean isDone();
	void execute();
	List<Task> develop();
}