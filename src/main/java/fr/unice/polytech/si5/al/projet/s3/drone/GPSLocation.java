package fr.unice.polytech.si5.al.projet.s3.drone;

public class GPSLocation {

	private double latitude;
	private double longitude;

	public GPSLocation(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public double distanceTo(GPSLocation location) {
		return Math.sqrt(
				Math.pow(location.latitude - this.latitude, 2)
				+ Math.pow(location.longitude - this.longitude, 2)
		);
	}
}