package fr.unice.polytech.si5.al.projet.central;

import java.util.*;

import fr.unice.polytech.si5.al.projet.shipping.PackageToShip;
import fr.unice.polytech.si5.al.projet.warehouse.Warehouse;

public class CentralWarehouse {

	private Collection<PackageToShip> packageToShips;
	private Collection<Warehouse> warehouses;
	private OrderDispatcher orderDispatcher;
	private Map<String,String> mapOrderIDToShippingRequestID = new HashMap<String,String>();
	private Collection<PackageToShip> currentDayPackageToShips;


	public CentralWarehouse() {
		//this.orders = this.buildFakeOrders();
		this.packageToShips = new LinkedList<PackageToShip>();
	}

	/**
	 * Dispatches all the orders of the current day.
	 */
	public void dispatchCurrentDayOrders() {
		WarehousesNetwork warehousesNetwork = new WarehousesNetwork(this.warehouses);
		OrderDispatcher orderDispatcher = new NaiveOrderDispatcher(warehousesNetwork);

		for (PackageToShip p: this.packageToShips) {
			Warehouse warehouse = orderDispatcher.dispatch(p);
			warehouse.addPackageToShip(p);

			//warehouse.assignCurrentDayOrder(o);
			//warehouse.addShippingRequest(request);
		}
	}

	public void sendApplicationRequests() {
		// TODO - implement CentralWarehouse.sendApplicationRequests
		throw new UnsupportedOperationException();
	}

	public void setCurrentDayPackageToShips(List<PackageToShip> currentDayPackageToShips) {
		this.currentDayPackageToShips = currentDayPackageToShips;
	}

	public void addWarehouse(Warehouse w) {
		this.warehouses.add(w);
	}
}