package fr.unice.polytech.al.drones.central;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by user on 05/11/2015.
 */
@Path("/shipping")
@Produces(MediaType.APPLICATION_JSON)
public interface ShippingService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Response addAShipping(String description);

	@GET
	@Path("/demo")
	Response getShippingDemoData();
}
