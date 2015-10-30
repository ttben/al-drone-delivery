package fr.unice.polytech.si5.al.projet.s3.central;

import fr.unice.polytech.si5.al.projet.s3.drone.GPSLocation;
import fr.unice.polytech.si5.al.projet.s3.utils.AddressToGPSConverter;
import fr.unice.polytech.si5.al.projet.s3.warehouse.Warehouse;

import java.util.Collection;

/**
 *
 */
public class NaiveOrderDispatcher implements OrderDispatcher {


	private final WarehousesNetwork warehousesNetwork;

	public NaiveOrderDispatcher(WarehousesNetwork warehousesNetwork) {
		this.warehousesNetwork = warehousesNetwork;
	}

	public Warehouse dispatch(Order order) {
		AddressToGPSConverter addressConverter = new AddressToGPSConverter();

		GPSLocation location = addressConverter.convert(order.getAddress());

		return this.warehousesNetwork.getClosestWarehouse(location);
	}
}
