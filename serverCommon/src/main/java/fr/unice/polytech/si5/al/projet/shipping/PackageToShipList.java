package fr.unice.polytech.si5.al.projet.shipping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sébastien on 11/11/2015.
 */
public class PackageToShipList {

    private ArrayList<PackageToShip> packageToShipList;

    public PackageToShipList(List<PackageToShip> packageToShipList) {
        this.packageToShipList = new ArrayList<>(packageToShipList);
    }

    public PackageToShipList() {
    }

    public void setPackageToShipList(ArrayList<PackageToShip> packageToShipList) {
        this.packageToShipList = packageToShipList;
    }

    public List<PackageToShip> getPackageToShipList() {
        return packageToShipList;
    }
}
