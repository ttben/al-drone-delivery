package fr.unice.polytech.al.drones.warehouse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.polytech.si5.al.projet.shipping.Shipping;

import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by Nabil on 09/11/2015.
 */
public class BoxServiceImpl implements BoxService {

    private ObjectMapper objectMapper = new ObjectMapper();

    public Response getBoxInformation(String id) {
        try {
            return Response.ok(objectMapper.writeValueAsString(BoxStorage.read(id))).build();
        } catch (JsonProcessingException e) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

    public Response addBox(String id, String box) {
        try {
            BoxStorage.add(id,objectMapper.readValue(box, Shipping.class));
        } catch (IOException e) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return Response.ok().build();
    }
}
