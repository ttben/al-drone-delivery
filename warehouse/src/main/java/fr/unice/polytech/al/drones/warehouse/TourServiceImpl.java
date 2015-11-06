package fr.unice.polytech.al.drones.warehouse;

import fr.unice.polytech.al.drones.tour.Tour;
import fr.unice.polytech.al.drones.tour.TourStorage;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;

import javax.ws.rs.core.Response;


/**
 * Created by user on 05/11/2015.
 */
public class TourServiceImpl implements TourService {

    private ObjectMapper objectMapper = new ObjectMapper();

    public Response getTour() {
        Tour tour = TourStorage.getLast();
        return Response.ok().build();
    }

    public Response newTour() {
        return Response.ok().build();
    }

    public Response setStatus(String id) {
        return Response.ok().build();
    }

    public Response setDeliveryStatus(String id, String deliveryId) {
        return Response.ok().build();
    }

    public Response setPackageDeliveryStatus(String id, String deliveryId, String packageId) {
        return Response.ok().build();
    }
}
