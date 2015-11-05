package fr.unice.polytech.al.drones.central;

import org.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class PackageServiceImpl implements PackageService {

	public Response updateStatus(String id, String status) {

		return Response.ok().build();
	}
}
