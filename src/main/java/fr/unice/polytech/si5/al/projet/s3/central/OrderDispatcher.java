package fr.unice.polytech.si5.al.projet.s3.central;

import fr.unice.polytech.si5.al.projet.s3.warehouse.Warehouse;

import java.util.List;

/**
 * This class must be a stub.
 * This class takes a list of client order and will transmit application request at one or more warehouses. It takes an order and split it in physical packages then return an application request. An application request can be a part of an order.
 * It will clusters delivery location and establish the closest fr.unice.polytech.si5.al.projet.s3.warehouse.
 */
public interface OrderDispatcher {

	//public ShippingRequest[] dispatch(List<Order> orders);
	public Warehouse dispatch(Order order);

}