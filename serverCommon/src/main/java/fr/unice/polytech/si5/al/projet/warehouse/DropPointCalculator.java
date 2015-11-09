package fr.unice.polytech.si5.al.projet.warehouse;

import fr.unice.polytech.si5.al.projet.drone.GPSLocation;
import fr.unice.polytech.si5.al.projet.shipping.Shipping;

import java.util.List;

public interface DropPointCalculator {

	GPSLocation computeDropPoint(List<Shipping> shippings);

}