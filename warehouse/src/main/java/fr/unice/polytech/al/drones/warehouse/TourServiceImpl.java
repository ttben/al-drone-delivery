package fr.unice.polytech.al.drones.warehouse;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import fr.unice.polytech.al.drones.tour.Delivery;
import fr.unice.polytech.al.drones.tour.DropPoint;
import fr.unice.polytech.al.drones.tour.Tour;
import fr.unice.polytech.al.drones.tour.TourStorage;
import fr.unice.polytech.si5.al.projet.central.Address;
import fr.unice.polytech.si5.al.projet.shipping.Dimensions;
import fr.unice.polytech.si5.al.projet.shipping.PackageToShip;
import fr.unice.polytech.si5.al.projet.shipping.PackageToShipList;
import fr.unice.polytech.si5.al.projet.shipping.Weight;
import fr.unice.polytech.si5.al.projet.truck.*;

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


    /**
     * For the payload, see the template.json file
     */
    public Response getTour() {
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        Tour tour = TourStorage.getLast();
        try {
            return Response.ok(objectMapper.writeValueAsString(tour)).build();
        } catch (IOException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     *
     * For the payload, see the template.json file
     * @param description
     * @return
     */
    public Response newTour(String description) {
        // Recup quelque chose ?
        // Construire un delivery a partir de ça
        // L'ajouter
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        Tour t;
        try {
            t = objectMapper.readValue(description,Tour.class);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Exception from newTour :" + e.getStackTrace());
            return Response.status(Response.Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
        }

        TourStorage.add(t);
        
        return Response.ok().build();
    }

    /**
     * To scrap
     * @param id
     * @return
     */
    public Response setStatus(String id) {
        return Response.ok().build();
    }

    /**
     * To scrap
     * @param id
     * @param deliveryId
     * @return
     */
    public Response setDeliveryStatus(String id, String deliveryId) {
        return Response.ok().build();
    }

    /**
     * To scrap
     * @param id
     * @param deliveryId
     * @param packageId
     * @return
     */
    public Response setPackageDeliveryStatus(String id, String deliveryId, String packageId) {
        return Response.ok().build();
    }
}
