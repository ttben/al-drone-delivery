package fr.unice.polytech.si5.al.projet.s3.central;

import java.util.*;

import fr.unice.polytech.si5.al.projet.s3.drone.GPSLocation;
import fr.unice.polytech.si5.al.projet.s3.utils.AddressToGPSConverter;
import fr.unice.polytech.si5.al.projet.s3.warehouse.*;

public class CentralWarehouse {

	private Collection<Order> orders;
	private Collection<Warehouse> warehouses;
	private OrderDispatcher orderDispatcher;
	private Map<String,String> mapOrderIDToShippingRequestID = new HashMap<String,String>();
	private List<Order> currentDayOrders;


	public CentralWarehouse() {
		//this.orders = this.buildFakeOrders();
		this.orders = new LinkedList<Order>();
	}

	/**
	 * Dispatches all the orders of the current day.
	 */
	public void dispatchCurrentDayOrders() {
		WarehousesNetwork warehousesNetwork = new WarehousesNetwork(this.warehouses);
		OrderDispatcher orderDispatcher = new NaiveOrderDispatcher(warehousesNetwork);

		for (Order o: this.orders) {
			Warehouse warehouse = orderDispatcher.dispatch(o);
			ShippingRequest request = new ShippingRequest();

			//warehouse.assignCurrentDayOrder(o);
			warehouse.addShippingRequest(request);
		}
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