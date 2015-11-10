package fr.unice.polytech.al.drones.warehouse;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.al.drones.tour.DropPoint;
import fr.unice.polytech.al.drones.tour.Tour;
import fr.unice.polytech.al.drones.tour.TourStorage;

import javax.ws.rs.core.Response;
import java.io.IOException;


/**
 * Created by user on 05/11/2015.
 */
public class TourServiceImpl implements TourService {

    private ObjectMapper objectMapper = new ObjectMapper();

    public Response getTour() {
        Tour tour = TourStorage.getLast();
        try {
            return Response.ok(objectMapper.writeValueAsString(tour)).build();
        } catch (IOException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    public Response newTour(String description) {
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
