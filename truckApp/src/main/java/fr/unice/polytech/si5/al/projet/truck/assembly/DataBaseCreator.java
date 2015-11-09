package fr.unice.polytech.si5.al.projet.truck.assembly;

import fr.unice.polytech.si5.al.projet.truck.domain.delivery.Delivery;
import fr.unice.polytech.si5.al.projet.truck.domain.drone.Drone;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Etienne Strobbe
 */
public class DataBaseCreator {

    private static List<Drone> drones = new ArrayList<>();
    private static List<Delivery> deliveries = new ArrayList<>();

    public static void init(String json){
        drones = Assembly.buildDrones(json);
        deliveries = Assembly.buildDelivery(json);
    }

    public static Drone getDroneFromID(String id){
        for(Drone drone : drones){
            if (drone.getID().getID().equals(id)){
                return drone;
            }
        }
        throw new IllegalArgumentException("Drone was not found -> bad ID");
    }

    public static Delivery getDeliveryFromID(String id){
        for (Delivery delivery : deliveries){
            if(delivery.getID().getID().equals(id)){
                return delivery;
            }
        }
        throw new IllegalArgumentException("Delivery was not found -> bad ID");
    }
}
