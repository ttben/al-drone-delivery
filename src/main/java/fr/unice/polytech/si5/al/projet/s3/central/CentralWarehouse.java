package fr.unice.polytech.si5.al.projet.s3.central;

import java.util.*;
import fr.unice.polytech.si5.al.projet.s3.warehouse.*;

public class CentralWarehouse {

	private Collection<Order> orders;
	private Collection<Warehouse> warehouses;
	private OrderBalancer orderBalancer;
	private Map<String,String> mapOrderIDToShippingRequestID = new HashMap<String,String>();


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

	private List<Order> buildFakeOrders() {
		List<Order> fakeOrders = new ArrayList<Order>();

		Item fakeItem = new Item();
		Location fakeLocation = new Location();

		List<Item> fakeListOfItems = new ArrayList<Item>();
		fakeListOfItems.add(fakeItem);

		Order fakeOrder = new Order(fakeListOfItems, fakeLocation);

		fakeOrders.add(fakeOrder);

		return fakeOrders;
	}
}