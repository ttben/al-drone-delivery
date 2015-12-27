package fr.unice.polytech.al.drones.tour;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by user on 06/11/2015.
 */
public class TourStorage {

    private static LinkedList<Tour> tourList = new LinkedList<Tour>();

    public static void add(Tour t){
        tourList.add(t);
    }

    public static Tour getLast(){
        return tourList.getLast();
    }

    static {

        ArrayList<Delivery> deliveries = new ArrayList<Delivery>();
        deliveries.add(new Delivery("drone1", "package1","drone3"));

        tourList.add(new Tour(new DropPoint("here",deliveries)));
    }
}
