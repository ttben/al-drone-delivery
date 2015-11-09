package fr.unice.polytech.al.drones.warehouse;

import java.util.HashMap;

/**
 * Created by Nabil on 09/11/2015.
 */
public class DroneStorage {

    private static HashMap<String,Drone> droneHashMap = new HashMap<String, Drone>();

    public static Drone read(String id){
        return droneHashMap.get(id);
    }

    static {
        droneHashMap.put("drone1",new Drone("drone1"));
    }
}
