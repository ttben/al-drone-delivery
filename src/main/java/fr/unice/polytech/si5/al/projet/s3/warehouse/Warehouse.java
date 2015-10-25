package fr.unice.polytech.si5.al.projet.s3.warehouse;

import fr.unice.polytech.si5.al.projet.s3.central.*;
import java.util.*;
import fr.unice.polytech.si5.al.projet.s3.drone.*;

public abstract class Warehouse {

	private CentralWarehouse central;
	private Collection<ShippingRequest> shippingRequest;
	private Collection<Drone> drones;
	private ShippingBalancer shppingBalancer;
	private Map<String,String> mapShippingRequestIDToShippingID = new HashMap<String,String>();

}