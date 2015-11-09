package fr.unice.polytech.al.drones.tour;

import fr.unice.polytech.si5.al.projet.central.Address;
import fr.unice.polytech.si5.al.projet.shipping.PackageToShip;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 06/11/2015.
 */
public class DropPoint {

    public Address getLocation() {
        return location;
    }

    public void setLocation(Address location) {
        this.location = location;
    }

    private Address location;

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    private List<Delivery> deliveries = new LinkedList<Delivery>();

    public DropPoint(Address l, Delivery pt){
        this.location = l;
        this.deliveries.add(pt);
    }
}
