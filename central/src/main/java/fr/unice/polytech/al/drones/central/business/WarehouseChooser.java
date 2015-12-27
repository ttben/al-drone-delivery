package fr.unice.polytech.al.drones.central.business;

import fr.unice.polytech.si5.al.projet.central.Address;

import java.util.Collection;
import java.util.HashMap;

/**
 * Created by sebastien on 11/11/15.
 */
public class WarehouseChooser {
    private HashMap<String, String> warehouses;

    public WarehouseChooser(HashMap<String, String> warehouses) {
        this.warehouses = warehouses;
    }

    public String choose(Address address) {
        Collection<String> ips = warehouses.values();
        return (String) ips.toArray()[0];
    }
}
