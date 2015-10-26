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

		double lattitudeSum = 0;
		double longtitudeSum = 0;

		for (GPSLocation loc : locations) {
			lattitudeSum += loc.getLattitude();
			longtitudeSum += loc.getLongitude();
		}

		return new GPSLocation(lattitudeSum / locations.size(), longtitudeSum / locations.size());
	}

}