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
        for(PackageToShip pck : toSend){
            deliveries.add(new Delivery("drone1", "box", "drone2"));
        }
        return new DropPoint("dropLocation", deliveries);
    }
}
