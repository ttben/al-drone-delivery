package fr.unice.polytech.al.drones.tour;

import fr.unice.polytech.si5.al.projet.central.Address;
import fr.unice.polytech.si5.al.projet.shipping.PackageToShip;
import fr.unice.polytech.si5.al.projet.truck.Drone;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 06/11/2015.
 */
public class DropPoint {

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    private String location;

    public List<Delivery> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    private List<Delivery> deliveries = new LinkedList<Delivery>();


    public DropPoint(String l, List<Delivery> pt){
        this.location = l;
        this.deliveries = (pt);
    }

    public DropPoint() {

    }
}
