package fr.unice.polytech.al.drones.warehouse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.al.drones.tour.Delivery;
import fr.unice.polytech.al.drones.tour.DropPoint;
import fr.unice.polytech.al.drones.tour.Tour;
import fr.unice.polytech.al.drones.tour.TourStorage;
import fr.unice.polytech.si5.al.projet.central.Address;
import fr.unice.polytech.si5.al.projet.shipping.Dimensions;
import fr.unice.polytech.si5.al.projet.shipping.PackageToShip;
import fr.unice.polytech.si5.al.projet.shipping.PackageToShipList;
import fr.unice.polytech.si5.al.projet.shipping.Weight;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by user on 05/11/2015.
 */
public class TourServiceImpl implements TourService {

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * [{
     * "location":"location info",
     * "deliveries":
     *     [
     *     {"drone":"droneId"
     *     "box":"boxId"
     *     "droneAlt":[
     *          "drone":"droneId"
     *      ]
     *      }
     *     ]
     * }]
     * @return
     */
    public Response getTour() {
        /**
        Tour tour = TourStorage.getLast();
        try {
            return Response.ok(objectMapper.writeValueAsString(tour.getDropPoints())).build();
        } catch (IOException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }**/
        List<PackageToShip> pkts = new ArrayList<>();

        PackageToShip pts = new PackageToShip(new Address("here"),new Weight(10),new Dimensions(10,10,19));
        PackageToShip pts2 = new PackageToShip(new Address("here"),new Weight(10),new Dimensions(10,10,19));
        pkts.add(pts);
        pkts.add(pts2);
        PackageToShipList pktsl = new PackageToShipList(pkts);

        try {
            return Response.ok(objectMapper.writeValueAsString(pktsl)).build();
        } catch (JsonProcessingException e) {
            System.out.println(e.getStackTrace());
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
    }

    /**
     * Payload :
     * {
     * "location":"location info",
     * "deliveries":
     *     [
     *     {"drone":"droneId"
     *     "box":"boxId"
     *     "droneAlt":[
     *          "drone":"droneId"
     *      ]
     *      }
     *     ]
     * }
     * @param description
     * @return
     */
    public Response newTour(String description) {
        // Recup quelque chose ?
        // Construire un delivery a partir de ça
        // L'ajouter
        List<DropPoint> list;
        try {
            list = Arrays.asList(objectMapper.readValue(description, DropPoint[].class));
        } catch (IOException e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
        }

        Tour t = new Tour(list);
        TourStorage.add(t);

        /**
         try {
         DropPoint dropPoint = objectMapper.readValue(description,DropPoint.class);
         TourStorage.getLast().addShipping(dropPoint);
         } catch (IOException e) {
         e.printStackTrace();
         return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
         }*/

        return Response.ok().build();
    }

    /**
     * lol who knows
     * @param id
     * @return
     */
    public Response setStatus(String id) {
        return Response.ok().build();
    }

    /**
     * lol who knows
     * @param id
     * @param deliveryId
     * @return
     */
    public Response setDeliveryStatus(String id, String deliveryId) {
        return Response.ok().build();
    }

    /**
     * lol who knows
     * @param id
     * @param deliveryId
     * @param packageId
     * @return
     */
    public Response setPackageDeliveryStatus(String id, String deliveryId, String packageId) {
        return Response.ok().build();
    }
}
