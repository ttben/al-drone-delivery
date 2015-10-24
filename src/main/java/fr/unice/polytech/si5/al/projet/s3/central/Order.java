package fr.unice.polytech.si5.al.projet.s3.central;

import java.util.*;

public class Order {

	Collection<Item> items;
	Location location;

	public Order(List<Item> items, Location location) {
		this.items = items;
		this.location = location;
	}

}