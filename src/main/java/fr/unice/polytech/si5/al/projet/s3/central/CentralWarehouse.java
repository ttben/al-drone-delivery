package fr.unice.polytech.si5.al.projet.s3.central;

import java.util.*;

import fr.unice.polytech.si5.al.projet.s3.drone.GPSLocation;
import fr.unice.polytech.si5.al.projet.s3.utils.AddressToGPSConverter;
import fr.unice.polytech.si5.al.projet.s3.warehouse.*;

public class CentralWarehouse {

	private Collection<Order> orders;
	private Collection<Warehouse> warehouses;
	private OrderBalancer orderBalancer;
	private Map<String,String> mapOrderIDToShippingRequestID = new HashMap<String,String>();
	private List<Order> currentDayOrders;


	public CentralWarehouse() {
		//this.orders = this.buildFakeOrders();
		this.orders = new LinkedList<Order>();
	}

	public void dispatchCurrentDayOrders() {
		AddressToGPSConverter addressConverter = new AddressToGPSConverter();

		for (Order o: this.orders) {
			GPSLocation	location = addressConverter.convert(o.getAddress());
			Warehouse closestWarehouse = this.getClosestWarehouse(location);

			closestWarehouse.assignCurrentDayOrder(o);
		}
	}

	private Warehouse getClosestWarehouse(GPSLocation location) {
		Warehouse closest = null;
		double distance = Double.POSITIVE_INFINITY;
		for (Warehouse w: this.warehouses) {
			double warehouseDistance = w.getLocation().distanceTo(location);
			if (warehouseDistance < distance) {
				closest = w;
				distance = warehouseDistance;
			}
		}
		return closest;
	}

	public void sendApplicationRequests() {
		// TODO - implement CentralWarehouse.sendApplicationRequests
		throw new UnsupportedOperationException();
	}

	@Deprecated
	private List<Order> buildFakeOrders() {
		List<Order> fakeOrders = new ArrayList<Order>();

		Item fakeItem = new Item();
		Address fakeAddress = new Address("fakeLocation");

		List<Item> fakeListOfItems = new ArrayList<Item>();
		fakeListOfItems.add(fakeItem);

		Order fakeOrder = new Order(fakeListOfItems, fakeAddress);

		fakeOrders.add(fakeOrder);

		return fakeOrders;
	}

	public void setCurrentDayOrders(List<Order> currentDayOrders) {
		this.currentDayOrders = currentDayOrders;
	}

	public void addWarehouse(Warehouse w) {
		this.warehouses.add(w);
	}
}