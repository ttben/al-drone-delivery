package fr.unice.polytech.al.drones.central;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by user on 05/11/2015.
 */

@Path("/package")
// Here we generate JSON data from scratch, one should use a framework instead
@Produces(MediaType.APPLICATION_JSON)
public interface PackageService {
    @PUT
    @Path("/{id}/status")
    @Consumes(MediaType.TEXT_PLAIN)
    Response updateStatus(@PathParam("{id}") String id, String status);


}
