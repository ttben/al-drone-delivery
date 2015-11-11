package fr.unice.polytech.al.drones.warehouse;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by Nabil on 09/11/2015.
 */
@Path("/box")
public interface BoxService {

    @GET
    @Path("/{id}")
    Response getBoxInformation(@PathParam("{id}") String id);

    @PUT
    @Path("/{id}")
    Response addBox(@PathParam("{id}")String id,String box);
}
