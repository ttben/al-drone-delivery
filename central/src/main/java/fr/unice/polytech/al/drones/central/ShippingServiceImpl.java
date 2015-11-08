package fr.unice.polytech.al.drones.central;

import javax.ws.rs.core.Response;


// Here we generate JSON data from scratch, one should use a framework instead
public class ShippingServiceImpl implements ShippingService {

	public Response addAShipping(String description) {

		return Response.ok().build();
	}
}
