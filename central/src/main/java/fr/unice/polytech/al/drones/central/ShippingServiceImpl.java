package fr.unice.polytech.al.drones.central;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


// Here we generate JSON data from scratch, one should use a framework instead
public class ShippingServiceImpl implements ShippingService {

        public Response test() {
                return Response.ok("ENORME").build();
        }

        public Response addAShipping(String description) {
        //ObjectMapper mapper = new ObjectMapper();
		// PackageToShip en reception
		//
        /*PackageToShip pkts;
        try {
            pkts = mapper.readValue(description, PackageToShip.class);
        } catch (Throwable t){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
*/
        Client client = ClientBuilder.newClient();
        WebTarget resource = client.target("http://localhost:8181/cxf/warehouse/tour");
        Entity e = Entity.entity(description, MediaType.APPLICATION_JSON);
        Invocation.Builder b = resource.request();

        return b.post(e);
	}
}
