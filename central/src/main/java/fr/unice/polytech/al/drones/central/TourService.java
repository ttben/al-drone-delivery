package fr.unice.polytech.al.drones.central;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
/**
 * Created by user on 05/11/2015.
 */
@Path("/tour")
@Produces(MediaType.APPLICATION_JSON)
public interface TourService {

    @GET
    Response getTour();

    @POST
    Response newTour();

}
