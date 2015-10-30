package fr.unice.polytech.si5.al.projet.s3.simulation;

import fr.unice.polytech.si5.al.projet.s3.central.CentralWarehouse;
import fr.unice.polytech.si5.al.projet.s3.central.Item;
import fr.unice.polytech.si5.al.projet.s3.central.Address;
import fr.unice.polytech.si5.al.projet.s3.central.Order;
import fr.unice.polytech.si5.al.projet.s3.drone.GPSLocation;
import fr.unice.polytech.si5.al.projet.s3.truck.DroneDeliveryApp;
import fr.unice.polytech.si5.al.projet.s3.truck.Truck;
import fr.unice.polytech.si5.al.projet.s3.truck.TruckDevice;
import fr.unice.polytech.si5.al.projet.s3.truck.TruckDriver;
import fr.unice.polytech.si5.al.projet.s3.warehouse.SimpleWarehouse;
import fr.unice.polytech.si5.al.projet.s3.warehouse.Warehouse;

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

		List<Order> orders = createOrders();
		central.setCurrentDayOrders(orders);

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

	public List<Order> createOrders() {
		List<Order> orders = new LinkedList<Order>();

		Item i1 = new Item("item1");
		Item i2 = new Item("item2");
		Item i3 = new Item("item3");
		Item i4 = new Item("item4");
		Item i5 = new Item("item5");
		Address address1 = new Address("derpboy");
		Order o1 = new Order(new LinkedList<Item>(Arrays.asList(i1, i2, i3)), address1);

		Address address2 = new Address("derpina");
		Order o2 = new Order(new LinkedList<Item>(Arrays.asList(i4, i5)), address2);

		orders.add(o1);
		orders.add(o2);

		return orders;
	}
}
