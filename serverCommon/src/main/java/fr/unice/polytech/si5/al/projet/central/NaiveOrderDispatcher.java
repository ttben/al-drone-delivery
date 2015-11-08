package fr.unice.polytech.si5.al.projet.central;

import fr.unice.polytech.si5.al.projet.drone.GPSLocation;
import fr.unice.polytech.si5.al.projet.shipping.PackageToShip;
import fr.unice.polytech.si5.al.projet.utils.AddressToGPSConverter;
import fr.unice.polytech.si5.al.projet.warehouse.Warehouse;

/**
 *
 */
public class NaiveOrderDispatcher implements OrderDispatcher {
	private final WarehousesNetwork warehousesNetwork;

	public NaiveOrderDispatcher(WarehousesNetwork warehousesNetwork) {
		this.warehousesNetwork = warehousesNetwork;
	}

	public Warehouse dispatch(PackageToShip p) {
		AddressToGPSConverter addressConverter = new AddressToGPSConverter();

		GPSLocation location = addressConverter.convert(p.getAddress());

		return this.warehousesNetwork.getClosestWarehouse(location);
	}
}
