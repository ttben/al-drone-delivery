package fr.unice.polytech.si5.al.projet.s3.central;

import fr.unice.polytech.si5.al.projet.s3.drone.GPSLocation;
import fr.unice.polytech.si5.al.projet.s3.warehouse.Warehouse;

import java.util.Collection;
import java.util.List;

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
