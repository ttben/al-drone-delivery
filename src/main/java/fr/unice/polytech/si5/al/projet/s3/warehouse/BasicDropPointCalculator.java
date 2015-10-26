package fr.unice.polytech.si5.al.projet.s3.warehouse;

import fr.unice.polytech.si5.al.projet.s3.drone.GPSLocation;
import fr.unice.polytech.si5.al.projet.s3.shipping.Shipping;

import java.util.LinkedList;
import java.util.List;

public class BasicDropPointCalculator implements DropPointCalculator {

	public GPSLocation computeDropPoint(List<Shipping> shippings) {
		List<GPSLocation> locations = new LinkedList<GPSLocation>();
		for (Shipping s : shippings) {
			locations.add(s.getLocation());
		}

		double latitudeSum = 0;
		double longitudeSum = 0;

		for (GPSLocation loc : locations) {
			latitudeSum += loc.getLatitude();
			longitudeSum += loc.getLongitude();
		}

		return new GPSLocation(latitudeSum / locations.size(), longitudeSum / locations.size());
	}

}