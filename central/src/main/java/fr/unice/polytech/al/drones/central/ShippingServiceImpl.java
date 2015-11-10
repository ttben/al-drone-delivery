package fr.unice.polytech.al.drones.central;

import fr.unice.polytech.al.drones.central.config.AddressesHolder;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;


// Here we generate JSON data from scratch, one should use a framework instead
public class ShippingServiceImpl implements ShippingService {

    public Response test() {

        return Response.ok("Ceci est un test").build();
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
        HashMap<String, String> addresses =
                (HashMap<String, String>) AddressesHolder.getReelAdressToIP();
        String ip = (String) addresses.values().toArray()[0];

        Client client = ClientBuilder.newClient();
        System.out.println("IP destination : " + ip);
        WebTarget resource = client.target(ip + "cxf/warehouse/tour");
        Entity e = Entity.entity(description, MediaType.APPLICATION_JSON);
        Invocation.Builder b = resource.request();

        return b.post(e);
    }
}
