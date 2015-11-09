package fr.unice.polytech.al.drones.warehouse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by Nabil on 09/11/2015.
 */
@Path("/drone")
public interface DroneService {
    @GET
    @Path("/{id}")
    Response getDroneInfo(@PathParam("{id}") String id);
}
