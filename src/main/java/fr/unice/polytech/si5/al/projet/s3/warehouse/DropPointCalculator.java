package fr.unice.polytech.si5.al.projet.s3.warehouse;

import fr.unice.polytech.si5.al.projet.s3.central.Item;
import fr.unice.polytech.si5.al.projet.s3.drone.GPSLocation;
import fr.unice.polytech.si5.al.projet.s3.shipping.Shipping;

import java.util.List;

public interface DropPointCalculator {

	GPSLocation computeDropPoint(List<Shipping> shippings);

}