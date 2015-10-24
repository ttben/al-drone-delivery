package fr.unice.polytech.si5.al.projet.s3.warehouse;

import fr.unice.polytech.si5.al.projet.s3.central.*;
import java.util.*;
import fr.unice.polytech.si5.al.projet.s3.drone.*;

public abstract class Warehouse {

	private CentralWarehouse central;
	private Collection<ApplicationRequest> applicationRequest;
	private Collection<Drone> drones;
	private ShippingBalancer shppingBalancer;

}