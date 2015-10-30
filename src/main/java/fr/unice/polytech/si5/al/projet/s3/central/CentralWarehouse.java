package fr.unice.polytech.si5.al.projet.s3.central;

import java.util.*;
import fr.unice.polytech.si5.al.projet.s3.warehouse.*;

public class CentralWarehouse {

	private Collection<Order> orders;
	private Collection<Warehouse> warehouses;
	private OrderBalancer orderBalancer;
	private Map<String,String> mapOrderIDToShippingRequestID = new HashMap<String,String>();
	private List<Order> currentDayOrders;


	public CentralWarehouse() {
		this.orders = this.buildFakeOrders();
	}

	public void dispatchOrders() {
		// TODO - implement CentralWarehouse.dispatchOrders
		throw new UnsupportedOperationException();
	}

	public void sendApplicationRequests() {
		// TODO - implement CentralWarehouse.sendApplicationRequests
		throw new UnsupportedOperationException();
	}

	@Deprecated
	private List<Order> buildFakeOrders() {
		List<Order> fakeOrders = new ArrayList<Order>();

		Item fakeItem = new Item();
		Location fakeLocation = new Location("fakeLocation");

		List<Item> fakeListOfItems = new ArrayList<Item>();
		fakeListOfItems.add(fakeItem);

		Order fakeOrder = new Order(fakeListOfItems, fakeLocation);

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