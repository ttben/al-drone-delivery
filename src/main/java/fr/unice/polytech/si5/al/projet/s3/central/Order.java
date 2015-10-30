package fr.unice.polytech.si5.al.projet.s3.central;

import java.util.*;

public class Order {

	Collection<Item> items;
	Address address;

	public Order(List<Item> items, Address address) {
		this.items = items;
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}
}