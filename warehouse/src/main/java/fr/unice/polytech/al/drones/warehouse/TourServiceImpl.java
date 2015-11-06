package fr.unice.polytech.al.drones.warehouse;

import org.json.JSONObject;

import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by user on 05/11/2015.
 */
public class TourServiceImpl implements TourService {

    public Response getTour(JSONObject description) {
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
