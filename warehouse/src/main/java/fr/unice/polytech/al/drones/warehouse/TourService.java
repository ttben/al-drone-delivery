package fr.unice.polytech.al.drones.warehouse;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by user on 05/11/2015.
 */
@Path("/tour")
@Produces(MediaType.APPLICATION_JSON)
public interface TourService {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    Response getTour();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Response newTour(String description);

    @Path("{id}/status")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    Response setStatus(@PathParam("id") String id);

    @Path("{id}/delivery/{deliveryId}/status")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    Response setDeliveryStatus(@PathParam("id") String id,
                               @PathParam("deliveryId") String deliveryId);

    @Path("{id}/delivery/{deliveryId}/package/{packageId}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    Response setPackageDeliveryStatus(@PathParam("id") String id,
                                      @PathParam("deliveryId") String deliveryId,
                                      @PathParam("packageId") String packageId);

}
