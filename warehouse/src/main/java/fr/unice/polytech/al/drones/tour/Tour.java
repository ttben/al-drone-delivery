package fr.unice.polytech.al.drones.tour;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 06/11/2015.
 */
public class Tour {
    public Tour(DropPoint dropPoint) {
        dropPoints.add(dropPoint);
    }

    public Tour(List<DropPoint> list) {
        dropPoints = list;
    }

    public List<DropPoint> getDropPoints() {
        return dropPoints;
    }

    public void setDropPoints(List<DropPoint> dropPoints) {
        this.dropPoints = dropPoints;
    }

    private List<DropPoint> dropPoints = new LinkedList<DropPoint>();

    public void addShipping(DropPoint sp){
        dropPoints.add(sp);
    }
}
