package fr.unice.polytech.si5.al.projet.s3.simulation;

import fr.unice.polytech.si5.al.projet.s3.central.CentralWarehouse;
import fr.unice.polytech.si5.al.projet.s3.central.Item;
import fr.unice.polytech.si5.al.projet.s3.central.Location;
import fr.unice.polytech.si5.al.projet.s3.central.Order;
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

		Warehouse w1 = new SimpleWarehouse(central);
		Warehouse w2 = new SimpleWarehouse(central);

		Truck t1 = initializeTruck(w1);
		Truck t2 = initializeTruck(w1);
		Truck t3 = initializeTruck(w2);

		TruckDriver t1d = t1.getDriver();

		List<Order> orders = createOrders();
		central.setCurrentDayOrders(orders);
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
		Location location1 = new Location("derpboy");
		Order o1 = new Order(new LinkedList<Item>(Arrays.asList(i1, i2, i3)), location1);

		Location location2 = new Location("derpina");
		Order o2 = new Order(new LinkedList<Item>(Arrays.asList(i4, i5)), location2);

		orders.add(o1);
		orders.add(o2);

		return orders;
	}
}
