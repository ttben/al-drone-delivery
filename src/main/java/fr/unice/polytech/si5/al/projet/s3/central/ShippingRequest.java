package fr.unice.polytech.si5.al.projet.s3.central;

import fr.unice.polytech.si5.al.projet.s3.shipping.Package;

/**
 * This object binds an order to a fr.unice.polytech.si5.al.projet.s3.warehouse. Allow loose binding and tracability
 */
public class ShippingRequest {

	private String ID;
	private Address address;
	private Package aPackage;

}