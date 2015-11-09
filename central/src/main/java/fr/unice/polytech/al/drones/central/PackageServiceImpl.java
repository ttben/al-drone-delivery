package fr.unice.polytech.al.drones.central;

import javax.ws.rs.core.Response;

public class PackageServiceImpl implements PackageService {

	public Response updateStatus(String id, String status) {

		return Response.ok().build();
	}
}
