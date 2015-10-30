package fr.unice.polytech.si5.al.projet.s3.central;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by sebastien on 30/10/15.
 */
@Path("/b")
@Produces(MediaType.APPLICATION_JSON)
public class b {

    @GET
    public Response get(){
        return Response.ok().build();
    }

}
