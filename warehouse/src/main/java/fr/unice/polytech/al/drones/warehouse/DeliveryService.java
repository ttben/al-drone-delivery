package fr.unice.polytech.al.drones.warehouse;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by Nabil on 09/11/2015.
 */
@Path("/delivery")
public interface DeliveryService {

    @GET
    @Path("/{id}")
    Response getDelivery();

    @PUT
    @Path("/{id}")
    Response addDelivery();
}
