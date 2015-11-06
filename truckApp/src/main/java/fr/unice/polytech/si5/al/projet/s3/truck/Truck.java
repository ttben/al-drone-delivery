package fr.unice.polytech.si5.al.projet.s3.truck;

public class Truck {
	private TruckDriver driver;
	private TruckDevice device;

	public void setDriver(TruckDriver driver) {
		this.driver = driver;
	}

	public void setDevice(TruckDevice device) {
		this.device = device;
	}

	public TruckDriver getDriver() {
		return driver;
	}
}