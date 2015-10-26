package fr.unice.polytech.si5.al.projet.s3.drone;

public class GPSLocation {

	private double lattitude;
	private double longitude;

	public GPSLocation(double lattitude, double longitude) {
		this.lattitude = lattitude;
		this.longitude = longitude;
	}

	public double getLatitude() {
		return lattitude;
	}

	public double getLongitude() {
		return longitude;
	}
}