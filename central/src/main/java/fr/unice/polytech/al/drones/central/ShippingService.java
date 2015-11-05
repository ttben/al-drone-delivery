package fr.unice.polytech.al.drones.central;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/shipping")
// Here we generate JSON data from scratch, one should use a framework instead
@Produces(MediaType.APPLICATION_JSON)
public class ShippingService {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addAShipping(JSONObject description) {

		return Response.ok().build();
	}
}
