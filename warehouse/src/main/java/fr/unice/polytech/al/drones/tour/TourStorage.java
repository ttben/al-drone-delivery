package fr.unice.polytech.al.drones.tour;

import fr.unice.polytech.si5.al.projet.central.Address;
import fr.unice.polytech.si5.al.projet.shipping.Dimensions;
import fr.unice.polytech.si5.al.projet.shipping.PackageToShip;
import fr.unice.polytech.si5.al.projet.shipping.Weight;

import java.util.LinkedList;

/**
 * Created by user on 06/11/2015.
 */
public class TourStorage {

    private static LinkedList<Tour> tourList = new LinkedList<Tour>();

    public static void create(DropPoint dropPoint, PackageToShip pts){
        tourList.add(new Tour(dropPoint,pts));
    }

    public static void create(Tour t){
        tourList.add(t);
    }

    public static Tour getLast(){
        return tourList.getLast();
    }

    static {
        tourList.add(new Tour(new DropPoint(new Address("there")), new PackageToShip(new Address("there"),new Weight(20.0),new Dimensions(70.0,10.0,10.0))));
        tourList.add(new Tour(new DropPoint(new Address("here")), new PackageToShip(new Address("here"),new Weight(10.0),new Dimensions(10.0,10.0,10.0))));
    }
}
