package fr.unice.polytech.al.drones.tour;

import fr.unice.polytech.si5.al.projet.shipping.PackageToShip;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 06/11/2015.
 */
public class Tour {

    private List<DropPoint> dropPointList = new LinkedList<DropPoint>();
    private List<PackageToShip> packageToShips = new LinkedList<PackageToShip>();

    public Tour(DropPoint dp, PackageToShip pt){
        getDropPointList().add(dp);
        getPackageToShips().add(pt);
    }


    public List<DropPoint> getDropPointList() {
        return dropPointList;
    }

    public void setDropPointList(List<DropPoint> dropPointList) {
        this.dropPointList = dropPointList;
    }

    public List<PackageToShip> getPackageToShips() {
        return packageToShips;
    }

    public void setPackageToShips(List<PackageToShip> packageToShips) {
        this.packageToShips = packageToShips;
    }
}
