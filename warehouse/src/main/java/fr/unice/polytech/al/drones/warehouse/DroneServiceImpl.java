package fr.unice.polytech.al.drones.warehouse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * Created by Nabil on 09/11/2015.
 */
public class DroneServiceImpl implements DroneService {

    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Returns :
     * {"drone":"droneName"}
     * @param id
     * @return
     */
    public Response getDroneInfo(String id) {
        try {
            return Response.ok(objectMapper.writeValueAsString(DroneStorage.read(id))).build();
        } catch (JsonProcessingException e) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }
}
