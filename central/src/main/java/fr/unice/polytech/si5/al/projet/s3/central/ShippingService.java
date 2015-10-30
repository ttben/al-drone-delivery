package fr.unice.polytech.si5.al.projet.s3.central;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by sebastien on 30/10/15.
 */

@Path("/shipping")
@Produces(MediaType.APPLICATION_JSON)
public class ShippingService {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createNewGenerator(String name) {

        return Response.ok().build();
    }

    @GET
    public Response get(){
        return Response.ok().build();
    }

}
