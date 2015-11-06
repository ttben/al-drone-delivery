package fr.unice.polytech.si5.al.projet.simulation;

import fr.unice.polytech.si5.al.projet.drone.GPSLocation;
import fr.unice.polytech.si5.al.projet.central.Address;
import fr.unice.polytech.si5.al.projet.central.CentralWarehouse;
import fr.unice.polytech.si5.al.projet.shipping.Dimensions;
import fr.unice.polytech.si5.al.projet.shipping.PackageToShip;
import fr.unice.polytech.si5.al.projet.shipping.Weight;
import fr.unice.polytech.si5.al.projet.truck.DroneDeliveryApp;
import fr.unice.polytech.si5.al.projet.truck.Truck;
import fr.unice.polytech.si5.al.projet.truck.TruckDevice;
import fr.unice.polytech.si5.al.projet.truck.TruckDriver;
import fr.unice.polytech.si5.al.projet.warehouse.SimpleWarehouse;
import fr.unice.polytech.si5.al.projet.warehouse.Warehouse;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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

		GPSLocation w1Location = new GPSLocation(43, 3);
		GPSLocation w2Location = new GPSLocation(42, 4);
		Warehouse w1 = new SimpleWarehouse(central, w1Location);
		Warehouse w2 = new SimpleWarehouse(central, w2Location);
		central.addWarehouse(w1);
		central.addWarehouse(w2);

		Truck t1 = initializeTruck(w1);
		Truck t2 = initializeTruck(w1);
		Truck t3 = initializeTruck(w2);

		TruckDriver t1d = t1.getDriver();

		List<PackageToShip> packageToShips = createPackages();
		central.setCurrentDayPackageToShips(packageToShips);

		central.dispatchCurrentDayOrders();
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

	public List<PackageToShip> createPackages() {
		return new LinkedList<PackageToShip>(Arrays.asList(
				new PackageToShip(new Address("derpboy"), new Weight(8.75), new Dimensions(.50, .100, .15)),
				new PackageToShip(new Address("derpina"), new Weight(.5), new Dimensions(.25, .25, .25))
		));
	}
}
