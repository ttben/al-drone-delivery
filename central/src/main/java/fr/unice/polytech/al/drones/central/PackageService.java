package fr.unice.polytech.al.drones.central;

import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/package")
// Here we generate JSON data from scratch, one should use a framework instead
@Produces(MediaType.APPLICATION_JSON)
public class PackageService {

	@PUT
	@Path("/{id}/status")
	@Consumes(MediaType.TEXT_PLAIN)
	public Response createNewGenerator(@PathParam("{id}") String id, String status) {

		return Response.ok().build();
	}
}
