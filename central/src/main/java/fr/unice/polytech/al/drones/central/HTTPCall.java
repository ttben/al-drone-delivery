package fr.unice.polytech.al.drones.central;

import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;

/**
 * Created by sebastien on 11/11/15.
 */
public class HTTPCall {

    public static Response POST(String ipAddress, String service, Entity media){
        Client client = ClientBuilder.newClient();
        WebTarget resource = client.target(ipAddress + service);
        Invocation.Builder b = resource.request();
        return b.post(media);
    }

}
