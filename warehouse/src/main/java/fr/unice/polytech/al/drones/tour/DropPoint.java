package fr.unice.polytech.al.drones.tour;

import fr.unice.polytech.si5.al.projet.central.Address;

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

    public DropPoint(Address l){
        this.location = l;
    }
}
