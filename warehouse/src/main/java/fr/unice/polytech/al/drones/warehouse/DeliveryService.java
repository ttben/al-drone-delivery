package fr.unice.polytech.al.drones.warehouse;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by Nabil on 09/11/2015.
 */
@Path("/delivery")
public interface DeliveryService {

    /**
     * Returns :
     * {
     *     "delivery":{
     *         "location":"location info"
     *     }
     * }
     * @param id delivery id
     * @return
     */
    @GET
    @Path("/{id}")
    Response getDelivery(@PathParam("{id}") String id);

    @POST
    Response addDelivery(String description);
}
