package fr.unice.polytech.si5.al.projet.s3.central;

import fr.unice.polytech.si5.al.projet.s3.warehouse.*;

/**
 * This object binds an order to a fr.unice.polytech.si5.al.projet.s3.warehouse. Allow loose binding and tracability
 */
public class ApplicationRequest {

	private Order order;
	private Warehouse targetWarehouse;
	private String ID;

}