package fr.unice.polytech.al.drones.central.business;

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
