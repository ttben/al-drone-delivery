package fr.unice.polytech.si5.al.projet.central;

import fr.unice.polytech.si5.al.projet.drone.GPSLocation;
import fr.unice.polytech.si5.al.projet.warehouse.Warehouse;

import java.util.Collection;

/**
 *
 */
public class WarehousesNetwork {

	private Collection<Warehouse> warehouses;

	public WarehousesNetwork(Collection<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}

	public Warehouse getClosestWarehouse(GPSLocation location) {
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
}
