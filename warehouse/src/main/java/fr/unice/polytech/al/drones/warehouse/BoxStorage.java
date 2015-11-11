package fr.unice.polytech.al.drones.warehouse;

import fr.unice.polytech.si5.al.projet.central.Address;
import fr.unice.polytech.si5.al.projet.drone.GPSLocation;
import fr.unice.polytech.si5.al.projet.shipping.*;

import java.util.HashMap;

/**
 * Created by Nabil on 09/11/2015.
 */
public class BoxStorage {

    private static HashMap<String,Shipping> shippingHashMap = new HashMap<String, Shipping>();

    public static Shipping read(String id) {
        return shippingHashMap.get(id);
    }

    static {
        shippingHashMap.put("box1",new Shipping(
                new PackageToShip(new Address("somewhere"),new Weight(10),new Dimensions(10,10,10)),
                ShippingStatus.NOT_SENT, new GPSLocation(10,10)));
    }

    public static void add(String id, Shipping shipping) {
        shippingHashMap.put(id,shipping);
    }
}
