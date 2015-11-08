package fr.unice.polytech.al.drones.tour;

import fr.unice.polytech.si5.al.projet.shipping.PackageToShip;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 06/11/2015.
 */
public class Tour {

    private List<DropPoint> dropPointList;
    private List<PackageToShip> packageToShips;

    public Tour(DropPoint dp, PackageToShip pt){
        dropPointList = new LinkedList<DropPoint>();
        packageToShips = new LinkedList<PackageToShip>();

        dropPointList.add(dp);
        packageToShips.add(pt);
    }


}
