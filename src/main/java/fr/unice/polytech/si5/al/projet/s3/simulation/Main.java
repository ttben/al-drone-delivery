package fr.unice.polytech.si5.al.projet.s3.simulation;

import fr.unice.polytech.si5.al.projet.s3.central.CentralWarehouse;
import fr.unice.polytech.si5.al.projet.s3.truck.DroneDeliveryApp;
import fr.unice.polytech.si5.al.projet.s3.truck.Truck;
import fr.unice.polytech.si5.al.projet.s3.truck.TruckDevice;
import fr.unice.polytech.si5.al.projet.s3.truck.TruckDriver;
import fr.unice.polytech.si5.al.projet.s3.warehouse.SimpleWarehouse;
import fr.unice.polytech.si5.al.projet.s3.warehouse.Warehouse;

/**
 *
 */
public class Main {

	public static void main(String[] args) {
		Main m = new Main();
	}

	public Main() {
		setup();
	}

	private void setup() {
		CentralWarehouse central = new CentralWarehouse();

		Warehouse w1 = new SimpleWarehouse();
		Warehouse w2 = new SimpleWarehouse();

		Truck t1 = initializeTruck(w1);
		Truck t2 = initializeTruck(w1);
		Truck t3 = initializeTruck(w2);

		TruckDriver t1d = t1.getDriver();
	}

	public Truck initializeTruck(Warehouse w) {
		Truck truck = new Truck();
		TruckDriver truckDriver = new TruckDriver();
		TruckDevice truckDevice = new TruckDevice();

		DroneDeliveryApp t1App = new DroneDeliveryApp();
		truckDevice.setApp(t1App);

		truck.setDevice(truckDevice);
		truck.setDriver(truckDriver);

		w.addDriver(truckDriver);
		w.addTruck(truck);

		return truck;
	}

}
