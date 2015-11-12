package fr.unice.polytech.al.drones.central.business;

import fr.unice.polytech.al.drones.central.config.AddressesHolder;
import fr.unice.polytech.si5.al.projet.central.Address;
import fr.unice.polytech.si5.al.projet.shipping.PackageToShip;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sebastien on 11/11/15.
 */
public class CentralModel {

    private static CentralModel m;
    private static WarehouseChooser w;

    public CentralModel(HashMap<String, String> warehouses) {
        w = new WarehouseChooser(warehouses);
    }

    private static int INDEX_DRONE = 1;
    private static int INDEX_BOX = 1;


    public CentralModel getInstance(){
        if(m == null)
            m = new CentralModel(AddressesHolder.loadAddresses());
        return m;
    }


    public static String chooseWarehouseIP(Address address) {
        return w.choose(address);
    }

    public static DropPoint getDropPoint(List<PackageToShip> toSend) {
        ArrayList<Delivery> deliveries = new ArrayList<Delivery>();

        deliveries.add(new Delivery("d1", "p1", "d3"));
        deliveries.add(new Delivery("d1", "p2", "d3"));
        deliveries.add(new Delivery("d2", "p3", "d3"));

        return new DropPoint("dropLocation", deliveries);
    }
}
