package fr.unice.polytech.si5.al.projet.s3.central;

import fr.unice.polytech.si5.al.projet.s3.shipping.*;
import fr.unice.polytech.si5.al.projet.s3.shipping.Package;
import fr.unice.polytech.si5.al.projet.s3.warehouse.*;

/**
 * This object binds an order to a fr.unice.polytech.si5.al.projet.s3.warehouse. Allow loose binding and tracability
 */
public class ShippingRequest {

	private String ID;
	private Location location;
	private Package aPackage;

}